package com.agh.order.entity;

import com.agh.order.model.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private String productName;
    private long productId;
    private long userId;
    private int quantity;
    private double price;
    private double discount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public void setOrderId(long orderId){
        this.orderId = orderId;
    }
    public long getOrderId() {
        return orderId;
    }

    public String getProductName() {
        return productName;
    }

    public long getProductId() {
        return productId;
    }

    public long getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public OrderInfo() {
    }


    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", productId=" + productId +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                ", status=" + status +
                ", createdAt=" + createdAt +

                '}';
    }
}
