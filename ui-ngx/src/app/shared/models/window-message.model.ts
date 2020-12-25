export type WindowMessageType = 'widgetException' | 'widgetEditModeInited' | 'widgetEditUpdated';

export interface WindowMessage {
  type: WindowMessageType;
  data?: any;
}
