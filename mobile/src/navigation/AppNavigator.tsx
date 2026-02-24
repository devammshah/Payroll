import React, { useEffect, useState } from 'react';
import { ActivityIndicator, View } from 'react-native';
import { NavigationContainer, DarkTheme } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Animated, { FadeIn } from 'react-native-reanimated';
import { LoginScreen } from '../screens/LoginScreen';
import { useAuth } from '../context/AuthContext';
import { DashboardScreen } from '../screens/DashboardScreen';
import { EmployeesScreen } from '../screens/EmployeesScreen';
import { PayrollScreen } from '../screens/PayrollScreen';
import { Employee, Payroll } from '../types';
import { fetchEmployees, fetchPayrolls } from '../api/payrollApi';

const Tab = createBottomTabNavigator();

export const AppNavigator = () => {
  const { token } = useAuth();
  const [employees, setEmployees] = useState<Employee[]>([]);
  const [payrolls, setPayrolls] = useState<Payroll[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const loadData = async () => {
      if (!token) return;
      setLoading(true);
      const [employeesRes, payrollRes] = await Promise.all([fetchEmployees(), fetchPayrolls()]);
      setEmployees(employeesRes.data);
      setPayrolls(payrollRes.data);
      setLoading(false);
    };

    loadData();
  }, [token]);

  return (
    <NavigationContainer theme={DarkTheme}>
      {!token ? (
        <LoginScreen />
      ) : loading ? (
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: '#04111C' }}>
          <ActivityIndicator size="large" color="#22D3EE" />
        </View>
      ) : (
        <Animated.View entering={FadeIn.duration(250)} style={{ flex: 1 }}>
          <Tab.Navigator
            screenOptions={{
              headerShown: false,
              tabBarStyle: {
                backgroundColor: '#06182A',
                borderTopColor: '#0C4A6E',
                height: 62,
                paddingBottom: 8,
                paddingTop: 8
              },
              tabBarActiveTintColor: '#2DD4BF',
              tabBarInactiveTintColor: '#A5F3FC'
            }}
          >
            <Tab.Screen name="Dashboard">{() => <DashboardScreen employees={employees} payrolls={payrolls} />}</Tab.Screen>
            <Tab.Screen name="Employees">{() => <EmployeesScreen employees={employees} />}</Tab.Screen>
            <Tab.Screen name="Payroll">{() => <PayrollScreen payrolls={payrolls} />}</Tab.Screen>
          </Tab.Navigator>
        </Animated.View>
      )}
    </NavigationContainer>
  );
};
