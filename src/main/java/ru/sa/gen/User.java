
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
 * User
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reputation",
    "region",
    "avatar",
    "last_seen",
    "ingame_name",
    "id",
    "status"
})
public class User {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("reputation")
    private Integer reputation;
    /**
     * Region
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("region")
    private Region region;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("avatar")
    private Object avatar;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("last_seen")
    private Date lastSeen;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("ingame_name")
    private String ingameName;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    private String id;
    /**
     * Status
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("status")
    private Status status;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("reputation")
    public Integer getReputation() {
        return reputation;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("reputation")
    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    /**
     * Region
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("region")
    public Region getRegion() {
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
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("avatar")
    public Object getAvatar() {
        return avatar;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("avatar")
    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("last_seen")
    public Date getLastSeen() {
        return lastSeen;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("last_seen")
    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("ingame_name")
    public String getIngameName() {
        return ingameName;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("ingame_name")
    public void setIngameName(String ingameName) {
        this.ingameName = ingameName;
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
     * Status
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    /**
     * Status
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(reputation).append(region).append(avatar).append(lastSeen).append(ingameName).append(id).append(status).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return new EqualsBuilder().append(reputation, rhs.reputation).append(region, rhs.region).append(avatar, rhs.avatar).append(lastSeen, rhs.lastSeen).append(ingameName, rhs.ingameName).append(id, rhs.id).append(status, rhs.status).isEquals();
    }

    public enum Region {

        EN("en");
        private final String value;
        private final static Map<String, Region> CONSTANTS = new HashMap<String, Region>();

        static {
            for (Region c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Region(String value) {
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
        public static Region fromValue(String value) {
            Region constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public enum Status {

        INGAME("ingame");
        private final String value;
        private final static Map<String, Status> CONSTANTS = new HashMap<String, Status>();

        static {
            for (Status c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Status(String value) {
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
        public static Status fromValue(String value) {
            Status constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
