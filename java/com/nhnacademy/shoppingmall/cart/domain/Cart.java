package com.nhnacademy.shoppingmall.cart.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Cart {
    Integer cartId;
    String userId;
    Integer quantity;
    Integer productId;
    LocalDateTime dateCreated;

    public Cart(Integer cartId, String userId, Integer quantity, Integer productId, LocalDateTime dateCreated) {
        this.cartId = cartId;
        this.userId = userId;
        this.quantity = quantity;
        this.productId = productId;
        this.dateCreated = dateCreated;
    }



    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(getCartId(), cart.getCartId()) && Objects.equals(getUserId(), cart.getUserId()) && Objects.equals(getQuantity(), cart.getQuantity()) && Objects.equals(getProductId(), cart.getProductId()) && Objects.equals(getDateCreated(), cart.getDateCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartId(), getUserId(), getQuantity(), getProductId(), getDateCreated());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId='" + userId + '\'' +
                ", quantity=" + quantity +
                ", productId=" + productId +
                ", dateCreated=" + dateCreated +
                '}';
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
//    CREATE TABLE `ShoppingCart` (
//            `CartID` int NOT NULL AUTO_INCREMENT,
//  `user_id` VARCHAR(50) NOT NULL,
//  `Quantity` int DEFAULT NULL,
//            `ProductID` int NOT NULL,
//            `DateCreated` datetime DEFAULT CURRENT_TIMESTAMP,
}
