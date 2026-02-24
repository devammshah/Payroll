import React from 'react';
import { AuthProvider } from './context/AuthContext';
import { AppNavigator } from './navigation/AppNavigator';

const App = () => (
  <AuthProvider>
    <AppNavigator />
  </AuthProvider>
);

export default App;
