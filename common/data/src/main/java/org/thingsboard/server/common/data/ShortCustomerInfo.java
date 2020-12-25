package org.thingsboard.server.common.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.thingsboard.server.common.data.id.CustomerId;

/**
 * Created by igor on 2/27/18.
 */

@AllArgsConstructor
public class ShortCustomerInfo {

    @Getter @Setter
    private CustomerId customerId;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private boolean isPublic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortCustomerInfo that = (ShortCustomerInfo) o;

        return customerId.equals(that.customerId);

    }

    @Override
    public int hashCode() {
        return customerId.hashCode();
    }
}
