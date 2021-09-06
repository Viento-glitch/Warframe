
package ru.sa.gen;

import java.util.Date;
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
 * Order
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "creation_date",
    "order_type",
    "quantity",
    "id",
    "user",
    "platinum",
    "item",
    "region",
    "visible",
    "last_update",
    "platform",
    "subtype",
    "mod_rank"
})
public class Order {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("creation_date")
    private Date creationDate;
    /**
     * OrderType
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("order_type")
    private OrderType orderType;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("quantity")
    private Integer quantity;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    private String id;
    /**
     * User
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("user")
    private User user;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("platinum")
    private Integer platinum;
    /**
     * Item
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("item")
    private Item item;
    /**
     * Region
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("region")
    private User.Region region;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("visible")
    private Boolean visible;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("last_update")
    private Date lastUpdate;
    /**
     * Platform
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("platform")
    private Platform platform;
    @JsonProperty("subtype")
    private String subtype;
    @JsonProperty("mod_rank")
    private Integer modRank;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("creation_date")
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * OrderType
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("order_type")
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * OrderType
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("order_type")
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * User
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    /**
     * User
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("platinum")
    public Integer getPlatinum() {
        return platinum;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("platinum")
    public void setPlatinum(Integer platinum) {
        this.platinum = platinum;
    }

    /**
     * Item
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("item")
    public Item getItem() {
        return item;
    }

    /**
     * Item
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("item")
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Region
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("region")
    public User.Region getRegion() {
        return region;
    }

    /**
     * Region
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("region")
    public void setRegion(User.Region region) {
        this.region = region;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("visible")
    public Boolean getVisible() {
        return visible;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("visible")
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("last_update")
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("last_update")
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Platform
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("platform")
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Platform
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("platform")
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @JsonProperty("subtype")
    public String getSubtype() {
        return subtype;
    }

    @JsonProperty("subtype")
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    @JsonProperty("mod_rank")
    public Integer getModRank() {
        return modRank;
    }

    @JsonProperty("mod_rank")
    public void setModRank(Integer modRank) {
        this.modRank = modRank;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(creationDate).append(orderType).append(quantity).append(id).append(user).append(platinum).append(item).append(region).append(visible).append(lastUpdate).append(platform).append(subtype).append(modRank).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Order) == false) {
            return false;
        }
        Order rhs = ((Order) other);
        return new EqualsBuilder().append(creationDate, rhs.creationDate).append(orderType, rhs.orderType).append(quantity, rhs.quantity).append(id, rhs.id).append(user, rhs.user).append(platinum, rhs.platinum).append(item, rhs.item).append(region, rhs.region).append(visible, rhs.visible).append(lastUpdate, rhs.lastUpdate).append(platform, rhs.platform).append(subtype, rhs.subtype).append(modRank, rhs.modRank).isEquals();
    }

    public enum OrderType {

        SELL("sell"),
        BUY("buy");
        private final String value;
        private final static Map<String, OrderType> CONSTANTS = new HashMap<String, OrderType>();

        static {
            for (OrderType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private OrderType(String value) {
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
        public static OrderType fromValue(String value) {
            OrderType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public enum Platform {

        PC("pc");
        private final String value;
        private final static Map<String, Platform> CONSTANTS = new HashMap<String, Platform>();

        static {
            for (Platform c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Platform(String value) {
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
        public static Platform fromValue(String value) {
            Platform constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
