import React, { PropsWithChildren } from 'react';
import { StyleSheet } from 'react-native';
import Animated, { FadeInDown, FadeOut, LinearTransition } from 'react-native-reanimated';

type Props = PropsWithChildren<{
  delay?: number;
}>;

export const GlassCard = ({ children, delay = 0 }: Props) => {
  return (
    <Animated.View
      entering={FadeInDown.delay(delay).duration(500)}
      exiting={FadeOut.duration(200)}
      layout={LinearTransition.duration(250)}
      style={styles.card}
    >
      {children}
    </Animated.View>
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 24,
    padding: 16,
    backgroundColor: 'rgba(255,255,255,0.08)',
    borderWidth: 1,
    borderColor: 'rgba(45,212,191,0.35)',
    marginBottom: 12,
    shadowColor: '#22D3EE',
    shadowOpacity: 0.25,
    shadowRadius: 18,
    shadowOffset: { width: 0, height: 8 },
    elevation: 10
  }
});
