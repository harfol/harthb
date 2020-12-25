import { JsonSettingsSchema } from '@shared/models/widget.models';
import { AnalogueGaugeSettings, analogueGaugeSettingsSchema } from '@home/components/widget/lib/analogue-gauge.models';
import { deepClone } from '@core/utils';

export interface AnalogueRadialGaugeSettings extends AnalogueGaugeSettings {
  startAngle: number;
  ticksAngle: number;
  needleCircleSize: number;
}

export function getAnalogueRadialGaugeSettingsSchema(): JsonSettingsSchema {
  const analogueRadialGaugeSettingsSchema = deepClone(analogueGaugeSettingsSchema);
  analogueRadialGaugeSettingsSchema.schema.properties =
  {...analogueRadialGaugeSettingsSchema.schema.properties, ...{
         startAngle: {
          title: 'Start ticks angle',
          type: 'number',
          default: 45
        },
        ticksAngle: {
          title: 'Ticks angle',
          type: 'number',
          default: 270
        },
        needleCircleSize: {
          title: 'Needle circle size',
          type: 'number',
          default: 10
  }}};
  analogueRadialGaugeSettingsSchema.form.unshift(
    'startAngle',
    'ticksAngle',
    'needleCircleSize'
  );
  return analogueRadialGaugeSettingsSchema;
}
