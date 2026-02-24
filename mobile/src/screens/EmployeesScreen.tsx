import React from 'react';
import { FlatList, StyleSheet, Text, View } from 'react-native';
import Animated, { FadeIn } from 'react-native-reanimated';
import { GlassCard } from '../components/GlassCard';
import { Employee } from '../types';

type Props = { employees: Employee[] };

export const EmployeesScreen = ({ employees }: Props) => (
  <View style={styles.container}>
    <Animated.Text entering={FadeIn.duration(450)} style={styles.header}>
      Team Directory
    </Animated.Text>
    <FlatList
      data={employees}
      showsVerticalScrollIndicator={false}
      contentContainerStyle={{ paddingBottom: 20 }}
      keyExtractor={(item) => item._id}
      renderItem={({ item, index }) => (
        <GlassCard delay={index * 50}>
          <Text style={styles.name}>{item.fullName}</Text>
          <Text style={styles.meta}>{item.designation} • {item.department}</Text>
          <Text style={styles.meta}>Base: ₹{item.baseSalary}</Text>
        </GlassCard>
      )}
    />
  </View>
);

const styles = StyleSheet.create({
  container: { flex: 1, padding: 16, backgroundColor: '#04111C' },
  header: { color: '#E0F2FE', fontSize: 24, fontWeight: '700', marginBottom: 10 },
  name: { color: '#ECFEFF', fontSize: 18, fontWeight: '600' },
  meta: { color: '#99F6E4', marginTop: 4 }
});
