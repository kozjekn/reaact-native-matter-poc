import { ViewProps } from 'react-native';
import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

export type Props = ViewProps;

const NativeView: React.ComponentType<Props> = requireNativeViewManager('MatterView');

export default function ExpoWebView(props: Props) {
  return <NativeView {...props} />;
}