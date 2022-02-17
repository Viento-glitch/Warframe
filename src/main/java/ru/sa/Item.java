
package ru.sa;

import java.util.List;
import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Generated("jsonschema2pojo")
public class Item {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("icon_format")
    @Expose
    private String iconFormat;
    @SerializedName("url_name")
    @Expose
    private String urlName;
    @SerializedName("tradable")
    @Expose
    private Boolean tradable;
    @SerializedName("game_ref")
    @Expose
    private GameRef gameRef;
    @SerializedName("en")
    @Expose
    private En en;
    @SerializedName("ru")
    @Expose
    private Ru ru;
    @SerializedName("rarity")
    @Expose
    private String rarity;
    @SerializedName("max_rank")
    @Expose
    private Integer maxRank;
    @SerializedName("trading_tax")
    @Expose
    private Integer tradingTax;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getIconFormat() {
        return iconFormat;
    }

    public void setIconFormat(String iconFormat) {
        this.iconFormat = iconFormat;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public Boolean getTradable() {
        return tradable;
    }

    public void setTradable(Boolean tradable) {
        this.tradable = tradable;
    }

    public GameRef getGameRef() {
        return gameRef;
    }

    public void setGameRef(GameRef gameRef) {
        this.gameRef = gameRef;
    }

    public En getEn() {
        return en;
    }

    public void setEn(En en) {
        this.en = en;
    }

    public Ru getRu() {
        return ru;
    }

    public void setRu(Ru ru) {
        this.ru = ru;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Integer getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(Integer maxRank) {
        this.maxRank = maxRank;
    }

    public Integer getTradingTax() {
        return tradingTax;
    }

    public void setTradingTax(Integer tradingTax) {
        this.tradingTax = tradingTax;
    }
}


@Generated("jsonschema2pojo")
class Ru {

    @SerializedName("item_name")
    @Expose
    private String itemName;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("wiki_link")
    @Expose
    private String wikiLink;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("drop")
    @Expose
    private List<Object> drop = null;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public List<Object> getDrop() {
        return drop;
    }

    public void setDrop(List<Object> drop) {
        this.drop = drop;
    }
}

@Generated("jsonschema2pojo")
class En {
    @SerializedName("item_name")
    @Expose
    private String itemName;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("wiki_link")
    @Expose
    private String wikiLink;
    @SerializedName("drop")
    @Expose
    private List<Object> drop = null;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public List<Object> getDrop() {
        return drop;
    }

    public void setDrop(List<Object> drop) {
        this.drop = drop;
    }


}

@Generated("jsonschema2pojo")
class GameRef {

    @SerializedName("uniq_name")
    @Expose
    private String uniqName;

    public String getUniqName() {
        return uniqName;
    }

    public void setUniqName(String uniqName) {
        this.uniqName = uniqName;
    }
}
