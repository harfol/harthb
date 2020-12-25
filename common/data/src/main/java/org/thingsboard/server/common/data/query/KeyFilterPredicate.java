package org.thingsboard.server.common.data.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringFilterPredicate.class, name = "STRING"),
        @JsonSubTypes.Type(value = NumericFilterPredicate.class, name = "NUMERIC"),
        @JsonSubTypes.Type(value = BooleanFilterPredicate.class, name = "BOOLEAN"),
        @JsonSubTypes.Type(value = ComplexFilterPredicate.class, name = "COMPLEX")})
public interface KeyFilterPredicate {

    @JsonIgnore
    FilterPredicateType getType();

}
