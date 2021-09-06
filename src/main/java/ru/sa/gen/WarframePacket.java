
package ru.sa.gen;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Welcome1Element
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "payload"
})
public class WarframePacket {

    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    private Type type;
    /**
     * Payload
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("payload")
    private Payload payload;

    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    public Type getType() {
        return type;
    }

    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Payload
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("payload")
    public Payload getPayload() {
        return payload;
    }

    /**
     * Payload
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(payload).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WarframePacket) == false) {
            return false;
        }
        WarframePacket rhs = ((WarframePacket) other);
        return new EqualsBuilder().append(type, rhs.type).append(payload, rhs.payload).isEquals();
    }

    public enum Type {

        WS_SUBSCRIPTIONS_MOST_RECENT_NEW_ORDER("@WS/SUBSCRIPTIONS/MOST_RECENT/NEW_ORDER");
        private final String value;
        private final static Map<String, Type> CONSTANTS = new HashMap<String, Type>();

        static {
            for (Type c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Type fromValue(String value) {
            Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
