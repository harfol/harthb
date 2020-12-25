package org.thingsboard.server.common.data.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

@Data
public class DynamicValue<T> {

    @JsonIgnore
    private T resolvedValue;

    @Getter
    private final DynamicValueSourceType sourceType;
    @Getter
    private final String sourceAttribute;

}
