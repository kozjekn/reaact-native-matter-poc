import MatterApiModule from './src/MatterApiModule';

export { default as MatterView, Props as MatterViewProps } from './src/MatterViewModule';

export function getAvailable(): string {
  return MatterApiModule.getAvailable();
}
