package com.nhnacademy.shoppingmall.cart.service;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.domain.Cart_Prod;
import java.util.List;

public interface CartService {
    Cart getCart(Integer cartId);
    void saveCart(Cart cart);
    void updateCart(Cart cart);
    void deleteCart(Integer cartId);
    List<Cart> findUserCart(String user_id);
    List<Cart> findAll();
    List<Cart_Prod> findCart_Prod(String userId);


    Integer findCartByProductId(Integer productId);

}
