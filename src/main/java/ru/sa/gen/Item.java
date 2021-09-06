
package ru.sa.gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * Item
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "thumb",
    "icon_format",
    "id",
    "tags",
    "sub_icon",
    "icon",
    "url_name",
    "en",
    "ru",
    "ko",
    "fr",
    "sv",
    "de",
    "zh-hant",
    "zh-hans",
    "pt",
    "es",
    "pl",
    "subtypes",
    "mod_max_rank",
    "ducats",
    "quantity_for_set"
})
public class Item {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("thumb")
    private String thumb;
    /**
     * IconFormat
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("icon_format")
    private IconFormat iconFormat;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    private String id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sub_icon")
    private Object subIcon;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("icon")
    private String icon;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("url_name")
    private String urlName;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("en")
    private En en;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("ru")
    private En ru;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("ko")
    private En ko;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("fr")
    private En fr;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("sv")
    private En sv;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("de")
    private En de;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("zh-hant")
    private En zhHant;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("zh-hans")
    private En zhHans;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("pt")
    private En pt;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("es")
    private En es;
    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("pl")
    private En pl;
    @JsonProperty("subtypes")
    private List<String> subtypes = new ArrayList<String>();
    @JsonProperty("mod_max_rank")
    private Integer modMaxRank;
    @JsonProperty("ducats")
    private Integer ducats;
    @JsonProperty("quantity_for_set")
    private Integer quantityForSet;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("thumb")
    public String getThumb() {
        return thumb;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("thumb")
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     * IconFormat
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("icon_format")
    public IconFormat getIconFormat() {
        return iconFormat;
    }

    /**
     * IconFormat
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("icon_format")
    public void setIconFormat(IconFormat iconFormat) {
        this.iconFormat = iconFormat;
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
     * 
     * (Required)
     * 
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sub_icon")
    public Object getSubIcon() {
        return subIcon;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sub_icon")
    public void setSubIcon(Object subIcon) {
        this.subIcon = subIcon;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("url_name")
    public String getUrlName() {
        return urlName;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("url_name")
    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("en")
    public En getEn() {
        return en;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("en")
    public void setEn(En en) {
        this.en = en;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("ru")
    public En getRu() {
        return ru;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("ru")
    public void setRu(En ru) {
        this.ru = ru;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("ko")
    public En getKo() {
        return ko;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("ko")
    public void setKo(En ko) {
        this.ko = ko;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("fr")
    public En getFr() {
        return fr;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("fr")
    public void setFr(En fr) {
        this.fr = fr;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("sv")
    public En getSv() {
        return sv;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("sv")
    public void setSv(En sv) {
        this.sv = sv;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("de")
    public En getDe() {
        return de;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("de")
    public void setDe(En de) {
        this.de = de;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("zh-hant")
    public En getZhHant() {
        return zhHant;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("zh-hant")
    public void setZhHant(En zhHant) {
        this.zhHant = zhHant;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("zh-hans")
    public En getZhHans() {
        return zhHans;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("zh-hans")
    public void setZhHans(En zhHans) {
        this.zhHans = zhHans;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("pt")
    public En getPt() {
        return pt;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("pt")
    public void setPt(En pt) {
        this.pt = pt;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("es")
    public En getEs() {
        return es;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("es")
    public void setEs(En es) {
        this.es = es;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("pl")
    public En getPl() {
        return pl;
    }

    /**
     * De
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("pl")
    public void setPl(En pl) {
        this.pl = pl;
    }

    @JsonProperty("subtypes")
    public List<String> getSubtypes() {
        return subtypes;
    }

    @JsonProperty("subtypes")
    public void setSubtypes(List<String> subtypes) {
        this.subtypes = subtypes;
    }

    @JsonProperty("mod_max_rank")
    public Integer getModMaxRank() {
        return modMaxRank;
    }

    @JsonProperty("mod_max_rank")
    public void setModMaxRank(Integer modMaxRank) {
        this.modMaxRank = modMaxRank;
    }

    @JsonProperty("ducats")
    public Integer getDucats() {
        return ducats;
    }

    @JsonProperty("ducats")
    public void setDucats(Integer ducats) {
        this.ducats = ducats;
    }

    @JsonProperty("quantity_for_set")
    public Integer getQuantityForSet() {
        return quantityForSet;
    }

    @JsonProperty("quantity_for_set")
    public void setQuantityForSet(Integer quantityForSet) {
        this.quantityForSet = quantityForSet;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(thumb).append(iconFormat).append(id).append(tags).append(subIcon).append(icon).append(urlName).append(en).append(ru).append(ko).append(fr).append(sv).append(de).append(zhHant).append(zhHans).append(pt).append(es).append(pl).append(subtypes).append(modMaxRank).append(ducats).append(quantityForSet).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item rhs = ((Item) other);
        return new EqualsBuilder().append(thumb, rhs.thumb).append(iconFormat, rhs.iconFormat).append(id, rhs.id).append(tags, rhs.tags).append(subIcon, rhs.subIcon).append(icon, rhs.icon).append(urlName, rhs.urlName).append(en, rhs.en).append(ru, rhs.ru).append(ko, rhs.ko).append(fr, rhs.fr).append(sv, rhs.sv).append(de, rhs.de).append(zhHant, rhs.zhHant).append(zhHans, rhs.zhHans).append(pt, rhs.pt).append(es, rhs.es).append(pl, rhs.pl).append(subtypes, rhs.subtypes).append(modMaxRank, rhs.modMaxRank).append(ducats, rhs.ducats).append(quantityForSet, rhs.quantityForSet).isEquals();
    }

    public enum IconFormat {

        LAND("land"),
        PORT("port");
        private final String value;
        private final static Map<String, IconFormat> CONSTANTS = new HashMap<String, IconFormat>();

        static {
            for (IconFormat c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private IconFormat(String value) {
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
        public static IconFormat fromValue(String value) {
            IconFormat constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
