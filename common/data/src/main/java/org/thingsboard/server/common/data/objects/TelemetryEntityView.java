package org.thingsboard.server.common.data.objects;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Basanets on 9/05/2017.
 */
@Data
@NoArgsConstructor
public class TelemetryEntityView implements Serializable {

    private List<String> timeseries;
    private AttributesEntityView attributes;

    public TelemetryEntityView(List<String> timeseries, AttributesEntityView attributes) {

        this.timeseries = new ArrayList<>(timeseries);
        this.attributes = attributes;
    }

    public TelemetryEntityView(TelemetryEntityView obj) {
        this(obj.getTimeseries(), obj.getAttributes());
    }
}
