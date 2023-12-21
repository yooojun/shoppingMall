package com.nhnacademy.shoppingmall.orderdetail.domain;

import java.util.Objects;

public class OrderDetails {
    Integer orderId;
    Integer productId;
    Integer quantity;
    Integer unitCost;

    public OrderDetails(Integer orderId, Integer productId, Integer quantity, Integer unitCost) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Integer unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails)) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(getOrderId(), that.getOrderId()) && Objects.equals(getProductId(), that.getProductId()) && Objects.equals(getQuantity(), that.getQuantity()) && Objects.equals(getUnitCost(), that.getUnitCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getProductId(), getQuantity(), getUnitCost());
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                '}';
    }
}