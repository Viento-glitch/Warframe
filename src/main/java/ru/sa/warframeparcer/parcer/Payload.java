package ru.sa.warframeparcer.parcer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class Payload {

    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;

    private String fullUrlLink;

    private String rusNameOfProduct;
    private String engNameOfProduct;
    private String koreaNameOfProduct;
    private String franceNameOfProduct;
    private String swedenNameOfProduct;
    private String germanyNameOfProduct;

    public String getFullUrlLink() {
        return fullUrlLink;
    }

    public void setFullUrlLink(String fullUrlLink) {
        this.fullUrlLink = fullUrlLink;
    }

    public String getRusNameOfProduct() {
        return rusNameOfProduct;
    }

    public void setRusNameOfProduct(String rusNameOfProduct) {
        this.rusNameOfProduct = rusNameOfProduct;
    }

    public String getEngNameOfProduct() {
        return engNameOfProduct;
    }

    public void setEngNameOfProduct(String engNameOfProduct) {
        this.engNameOfProduct = engNameOfProduct;
    }

    public String getKoreaNameOfProduct() {
        return koreaNameOfProduct;
    }

    public void setKoreaNameOfProduct(String koreaNameOfProduct) {
        this.koreaNameOfProduct = koreaNameOfProduct;
    }

    public String getFranceNameOfProduct() {
        return franceNameOfProduct;
    }

    public void setFranceNameOfProduct(String franceNameOfProduct) {
        this.franceNameOfProduct = franceNameOfProduct;
    }

    public String getSwedenNameOfProduct() {
        return swedenNameOfProduct;
    }

    public void setSwedenNameOfProduct(String swedenNameOfProduct) {
        this.swedenNameOfProduct = swedenNameOfProduct;
    }

    public String getGermanyNameOfProduct() {
        return germanyNameOfProduct;
    }

    public void setGermanyNameOfProduct(String germanyNameOfProduct) {
        this.germanyNameOfProduct = germanyNameOfProduct;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
