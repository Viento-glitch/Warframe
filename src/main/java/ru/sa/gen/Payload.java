
package ru.sa.gen;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Payload
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "order"
})
public class Payload {

    /**
     * Order
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("order")
    private Order order;

    /**
     * Order
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("order")
    public Order getOrder() {
        return order;
    }

    /**
     * Order
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("order")
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(order).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Payload) == false) {
            return false;
        }
        Payload rhs = ((Payload) other);
        return new EqualsBuilder().append(order, rhs.order).isEquals();
    }

}
