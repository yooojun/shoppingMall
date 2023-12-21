package com.nhnacademy.shoppingmall.cart.domain;

import java.util.Objects;

public class Cart_Prod {
    Integer cartId;
    String productImage;
    String modelName;
    Integer unitCost;
    Integer quantity;



    public Cart_Prod(Integer cartId, String productImage, String modelName, Integer unitCost, Integer quantity) {

        this.cartId = cartId;
        this.productImage = productImage;
        this.modelName = modelName;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getModelName() {
        return modelName;
    }

    public Integer getUnitCost() {
        return unitCost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setUnitCost(Integer unitCost) {
        this.unitCost = unitCost;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart_Prod)) return false;
        Cart_Prod cartProd = (Cart_Prod) o;
        return Objects.equals(getProductImage(), cartProd.getProductImage()) && Objects.equals(getModelName(), cartProd.getModelName()) && Objects.equals(getUnitCost(), cartProd.getUnitCost()) && Objects.equals(getQuantity(), cartProd.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductImage(), getModelName(), getUnitCost(), getQuantity());
    }

    @Override
    public String toString() {
        return "Cart_Prod{" +
                "productImage='" + productImage + '\'' +
                ", modelName='" + modelName + '\'' +
                ", unitPrice=" + unitCost +
                ", quantity=" + quantity +
                '}';
    }
}
