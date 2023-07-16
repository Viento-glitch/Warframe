package ru.sa.warframeparcer.parcer;

public class BestOrders {
    Order stockBestSeller ;
    Order stockSecondSeller ;
    Order stockBestBuyer ;
    Order stockSecondBuyer ;
    Order fullBestSeller ;
    Order fullSecondSeller ;
    Order fullBestBuyer ;
    Order fullSecondBuyer ;

    public BestOrders(Order stockBestSeller, Order stockSecondSeller, Order stockBestBuyer, Order stockSecondBuyer, Order fullBestSeller, Order fullSecondSeller, Order fullBestBuyer, Order fullSecondBuyer) {
        this.stockBestSeller = stockBestSeller;
        this.stockSecondSeller = stockSecondSeller;
        this.stockBestBuyer = stockBestBuyer;
        this.stockSecondBuyer = stockSecondBuyer;
        this.fullBestSeller = fullBestSeller;
        this.fullSecondSeller = fullSecondSeller;
        this.fullBestBuyer = fullBestBuyer;
        this.fullSecondBuyer = fullSecondBuyer;
    }

    public Order getStockBestSeller() {
        return stockBestSeller;
    }

    public void setStockBestSeller(Order stockBestSeller) {
        this.stockBestSeller = stockBestSeller;
    }

    public Order getStockSecondSeller() {
        return stockSecondSeller;
    }

    public void setStockSecondSeller(Order stockSecondSeller) {
        this.stockSecondSeller = stockSecondSeller;
    }

    public Order getStockBestBuyer() {
        return stockBestBuyer;
    }

    public void setStockBestBuyer(Order stockBestBuyer) {
        this.stockBestBuyer = stockBestBuyer;
    }

    public Order getStockSecondBuyer() {
        return stockSecondBuyer;
    }

    public void setStockSecondBuyer(Order stockSecondBuyer) {
        this.stockSecondBuyer = stockSecondBuyer;
    }

    public Order getFullBestSeller() {
        return fullBestSeller;
    }

    public void setFullBestSeller(Order fullBestSeller) {
        this.fullBestSeller = fullBestSeller;
    }

    public Order getFullSecondSeller() {
        return fullSecondSeller;
    }

    public void setFullSecondSeller(Order fullSecondSeller) {
        this.fullSecondSeller = fullSecondSeller;
    }

    public Order getFullBestBuyer() {
        return fullBestBuyer;
    }

    public void setFullBestBuyer(Order fullBestBuyer) {
        this.fullBestBuyer = fullBestBuyer;
    }

    public Order getFullSecondBuyer() {
        return fullSecondBuyer;
    }

    public void setFullSecondBuyer(Order fullSecondBuyer) {
        this.fullSecondBuyer = fullSecondBuyer;
    }
}
