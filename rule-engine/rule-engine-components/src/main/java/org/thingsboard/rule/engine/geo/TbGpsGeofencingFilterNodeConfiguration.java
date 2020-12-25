package org.thingsboard.rule.engine.geo;

import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.msg.session.SessionMsgType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ashvayka on 19.01.18.
 */
@Data
public class TbGpsGeofencingFilterNodeConfiguration implements NodeConfiguration<TbGpsGeofencingFilterNodeConfiguration> {

    private String latitudeKeyName;
    private String longitudeKeyName;
    private boolean fetchPerimeterInfoFromMessageMetadata;

    private PerimeterType perimeterType;

    //For Polygons
    private String polygonsDefinition;

    //For Circles
    private Double centerLatitude;
    private Double centerLongitude;
    private Double range;
    private RangeUnit rangeUnit;

    @Override
    public TbGpsGeofencingFilterNodeConfiguration defaultConfiguration() {
        TbGpsGeofencingFilterNodeConfiguration configuration = new TbGpsGeofencingFilterNodeConfiguration();
        configuration.setLatitudeKeyName("latitude");
        configuration.setLongitudeKeyName("longitude");
        configuration.setFetchPerimeterInfoFromMessageMetadata(true);
        return configuration;
    }
}
