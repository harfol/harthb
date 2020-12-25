
import L from 'leaflet';
import LeafletMap from '../leaflet-map';
import { DEFAULT_ZOOM_LEVEL, UnitedMapSettings } from '../map-models';
import { WidgetContext } from '@home/models/widget-component.models';

export class TencentMap extends LeafletMap {
  constructor(ctx: WidgetContext, $container, options: UnitedMapSettings) {
    super(ctx, $container, options);
    const txUrl = 'http://rt{s}.map.gtimg.com/realtimerender?z={z}&x={x}&y={y}&type=vector&style=0';
    const map = L.map($container, {
      editable: !!options.editablePolygon
    }).setView(options?.defaultCenterPosition, options?.defaultZoomLevel || DEFAULT_ZOOM_LEVEL);
    const txLayer = L.tileLayer(txUrl, {
      subdomains: '0123',
      tms: true,
      attribution: '&copy;2020 Tencent - GS(2018)2236号- Data&copy; NavInfo'
    }).addTo(map);
    txLayer.addTo(map);
    super.initSettings(options);
    super.setMap(map);
  }
}
