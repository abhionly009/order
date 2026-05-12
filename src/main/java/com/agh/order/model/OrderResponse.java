package com.agh.order.model;


import com.agh.order.entity.OrderInfo;

public class OrderResponse {



    private long orderId;
    private String productName;
    private long productId;
    private int quantity;
    private double price;
    private double discount;

    public OrderResponse() {
    }

    public OrderResponse mapToResponse(OrderInfo order){

        this.setOrderId(order.getOrderId());
        this.setProductName(order.getProductName());
        this.setProductId(order.getProductId());
        this.setQuantity(order.getQuantity());
        this.setPrice(order.getPrice());
        this.setDiscount(order.getDiscount());

        double finalPrice = order.getPrice() - order.getDiscount();
        return this;
    }


    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
