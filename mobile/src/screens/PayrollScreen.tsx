import React from 'react';
import { Alert, FlatList, Pressable, StyleSheet, Text, View } from 'react-native';
import Animated, { FadeIn } from 'react-native-reanimated';
import { generateAndSharePayslip } from '../utils/payslipPdf';
import { Payroll } from '../types';
import { GlassCard } from '../components/GlassCard';

type Props = { payrolls: Payroll[] };

export const PayrollScreen = ({ payrolls }: Props) => {
  const onSharePayslip = async (payroll: Payroll) => {
    try {
      const path = await generateAndSharePayslip(payroll);
      Alert.alert('PDF generated', `Saved at ${path}`);
    } catch {
      Alert.alert('PDF error', 'Unable to generate or share payslip.');
    }
  };

  return (
    <View style={styles.container}>
      <Animated.Text entering={FadeIn.duration(450)} style={styles.header}>
        Monthly Payroll
      </Animated.Text>
      <FlatList
        data={payrolls}
        keyExtractor={(item) => item._id}
        contentContainerStyle={{ paddingBottom: 30 }}
        renderItem={({ item, index }) => (
          <GlassCard delay={index * 45}>
            <Text style={styles.name}>{item.employee.fullName}</Text>
            <Text style={styles.meta}>Period: {item.month}/{item.year}</Text>
            <Text style={styles.net}>Net Salary: ₹{item.netSalary}</Text>
            <Pressable onPress={() => onSharePayslip(item)} style={({ pressed }) => [styles.button, pressed && styles.pressed]}>
              <Text style={styles.buttonText}>Preview / Download / Share PDF</Text>
            </Pressable>
          </GlassCard>
        )}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#04111C', padding: 16 },
  header: { color: '#E0F2FE', fontSize: 24, fontWeight: '700', marginBottom: 10 },
  name: { color: '#E0F2FE', fontSize: 17, fontWeight: '600' },
  meta: { color: '#A5F3FC', marginTop: 4 },
  net: { color: '#2DD4BF', marginTop: 6, fontWeight: '700' },
  button: { marginTop: 10, borderRadius: 10, padding: 10, backgroundColor: '#0EA5E9' },
  pressed: { opacity: 0.85 },
  buttonText: { color: '#082F49', fontWeight: '700' }
});
