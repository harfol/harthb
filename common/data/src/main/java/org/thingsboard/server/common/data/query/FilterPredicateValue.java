package org.thingsboard.server.common.data.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class FilterPredicateValue<T> {

    @Getter
    private final T defaultValue;
    @Getter
    private final T userValue;
    @Getter
    private final DynamicValue<T> dynamicValue;

    public FilterPredicateValue(T defaultValue) {
        this(defaultValue, null, null);
    }

    @JsonCreator
    public FilterPredicateValue(@JsonProperty("defaultValue") T defaultValue,
                                @JsonProperty("userValue") T userValue,
                                @JsonProperty("dynamicValue") DynamicValue<T> dynamicValue) {
        this.defaultValue = defaultValue;
        this.userValue = userValue;
        this.dynamicValue = dynamicValue;
    }

    @JsonIgnore
    public T getValue() {
        if (this.userValue != null) {
            return this.userValue;
        } else {
            if (this.dynamicValue != null && this.dynamicValue.getResolvedValue() != null) {
                return this.dynamicValue.getResolvedValue();
            } else {
                return defaultValue;
            }
        }
    }

    public static FilterPredicateValue<Double> fromDouble(double value) {
        return new FilterPredicateValue<>(value);
    }

    public static FilterPredicateValue<String> fromString(String value) {
        return new FilterPredicateValue<>(value);
    }

    public static FilterPredicateValue<Boolean> fromBoolean(boolean value) {
        return new FilterPredicateValue<>(value);
    }
}
