import React, { useEffect, useRef, useState } from 'react';
import {
  Alert,
  Animated,
  Easing,
  KeyboardAvoidingView,
  Platform,
  Pressable,
  StyleSheet,
  Text,
  TextInput,
  View
} from 'react-native';
import { useAuth } from '../context/AuthContext';

export const LoginScreen = () => {
  const { login } = useAuth();
  const [username, setUsername] = useState('admin');
  const [password, setPassword] = useState('admin@123');
  const [loading, setLoading] = useState(false);

  const fade = useRef(new Animated.Value(0)).current;
  const slide = useRef(new Animated.Value(22)).current;
  const scale = useRef(new Animated.Value(0.98)).current;

  useEffect(() => {
    Animated.parallel([
      Animated.timing(fade, {
        toValue: 1,
        duration: 500,
        useNativeDriver: true,
        easing: Easing.out(Easing.cubic)
      }),
      Animated.timing(slide, {
        toValue: 0,
        duration: 550,
        useNativeDriver: true,
        easing: Easing.out(Easing.exp)
      }),
      Animated.spring(scale, {
        toValue: 1,
        useNativeDriver: true,
        speed: 12,
        bounciness: 5
      })
    ]).start();
  }, [fade, scale, slide]);

  const handleLogin = async () => {
    try {
      setLoading(true);
      await login(username, password);
    } catch {
      Alert.alert('Authentication failed', 'Invalid admin credentials');
    } finally {
      setLoading(false);
    }
  };

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === 'ios' ? 'padding' : undefined}
      style={styles.container}
    >
      <Animated.View
        style={{
          opacity: fade,
          transform: [{ translateY: slide }, { scale }]
        }}
      >
        <Text style={styles.heading}>Payroll Pro</Text>
        <Text style={styles.sub}>Secure admin workspace</Text>
        <TextInput value={username} onChangeText={setUsername} style={styles.input} placeholder="Username" placeholderTextColor="#0C4A6E" />
        <TextInput value={password} onChangeText={setPassword} style={styles.input} placeholder="Password" placeholderTextColor="#0C4A6E" secureTextEntry />
        <Pressable onPress={handleLogin} style={({ pressed }) => [styles.button, pressed && styles.buttonPressed]}>
          <Text style={styles.buttonText}>{loading ? 'Signing in...' : 'Secure Login'}</Text>
        </Pressable>
      </Animated.View>
      <View style={styles.glow} />
    </KeyboardAvoidingView>
  );
};

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', padding: 20, backgroundColor: '#031A2E' },
  heading: { color: '#67E8F9', fontSize: 36, fontWeight: '700', marginBottom: 6 },
  sub: { color: '#99F6E4', marginBottom: 24, fontSize: 15 },
  input: { backgroundColor: '#E0F2FE', borderRadius: 14, marginBottom: 12, paddingHorizontal: 14, height: 50 },
  button: { backgroundColor: '#14B8A6', borderRadius: 14, alignItems: 'center', padding: 14 },
  buttonPressed: { opacity: 0.82, transform: [{ scale: 0.99 }] },
  buttonText: { color: '#022C22', fontWeight: '700' },
  glow: {
    position: 'absolute',
    width: 220,
    height: 220,
    borderRadius: 110,
    backgroundColor: 'rgba(34,211,238,0.16)',
    bottom: -40,
    right: -40
  }
});
