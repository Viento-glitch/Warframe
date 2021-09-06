
package ru.sa.gen;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * De
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "item_name"
})
public class En {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("item_name")
    private String itemName;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("item_name")
    public String getItemName() {
        return itemName;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("item_name")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(itemName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof En) == false) {
            return false;
        }
        En rhs = ((En) other);
        return new EqualsBuilder().append(itemName, rhs.itemName).isEquals();
    }

}
