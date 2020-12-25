import { JsonSettingsSchema } from '@shared/models/widget.models';
import { AnalogueGaugeSettings, analogueGaugeSettingsSchema } from '@home/components/widget/lib/analogue-gauge.models';
import { deepClone } from '@core/utils';

export interface AnalogueLinearGaugeSettings extends AnalogueGaugeSettings {
  barStrokeWidth: number;
  colorBarStroke: string;
  colorBar: string;
  colorBarEnd: string;
  colorBarProgress: string;
  colorBarProgressEnd: string;
}

export function getAnalogueLinearGaugeSettingsSchema(): JsonSettingsSchema {
  const analogueLinearGaugeSettingsSchema = deepClone(analogueGaugeSettingsSchema);
  analogueLinearGaugeSettingsSchema.schema.properties =
    {...analogueLinearGaugeSettingsSchema.schema.properties, ...{
        barStrokeWidth: {
          title: 'Bar stroke width',
          type: 'number',
          default: 2.5
        },
        colorBarStroke: {
          title: 'Bar stroke color',
          type: 'string',
          default: null
        },
        colorBar: {
          title: 'Bar background color',
          type: 'string',
          default: '#fff'
        },
        colorBarEnd: {
          title: 'Bar background color - end gradient',
          type: 'string',
          default: '#ddd'
        },
        colorBarProgress: {
          title: 'Progress bar color',
          type: 'string',
          default: null
        },
        colorBarProgressEnd: {
          title: 'Progress bar color - end gradient',
          type: 'string',
          default: null
        }}};
  analogueLinearGaugeSettingsSchema.form.unshift(
    'barStrokeWidth',
    {
      key: 'colorBarStroke',
      type: 'color'
    },
    {
      key: 'colorBar',
      type: 'color'
    },
    {
      key: 'colorBarEnd',
      type: 'color'
    },
    {
      key: 'colorBarProgress',
      type: 'color'
    },
    {
      key: 'colorBarProgressEnd',
      type: 'color'
    }
  );
  return analogueLinearGaugeSettingsSchema;
}
