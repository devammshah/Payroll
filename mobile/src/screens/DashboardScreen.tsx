import React, { useMemo } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Animated, { FadeInUp, SlideInRight } from 'react-native-reanimated';
import { GlassCard } from '../components/GlassCard';
import { Employee, Payroll } from '../types';

type Props = { employees: Employee[]; payrolls: Payroll[] };

export const DashboardScreen = ({ employees, payrolls }: Props) => {
  const totalPayout = useMemo(() => payrolls.reduce((sum, p) => sum + p.netSalary, 0), [payrolls]);

  return (
    <View style={styles.container}>
      <Animated.Text entering={SlideInRight.springify().damping(18)} style={styles.header}>
        Overview
      </Animated.Text>
      <Animated.View entering={FadeInUp.duration(450)}>
        <GlassCard delay={80}>
          <Text style={styles.title}>Employees</Text>
          <Text style={styles.value}>{employees.length}</Text>
        </GlassCard>
      </Animated.View>
      <Animated.View entering={FadeInUp.duration(650)}>
        <GlassCard delay={130}>
          <Text style={styles.title}>Payrolls Generated</Text>
          <Text style={styles.value}>{payrolls.length}</Text>
        </GlassCard>
      </Animated.View>
      <Animated.View entering={FadeInUp.duration(850)}>
        <GlassCard delay={180}>
          <Text style={styles.title}>Monthly Payout</Text>
          <Text style={styles.value}>₹{totalPayout.toFixed(2)}</Text>
        </GlassCard>
      </Animated.View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#04111C', padding: 16 },
  header: { color: '#E0F2FE', fontSize: 26, fontWeight: '700', marginBottom: 14 },
  title: { color: '#A5F3FC', fontSize: 16 },
  value: { color: '#E0F2FE', fontSize: 30, fontWeight: '700', marginTop: 8 }
});
