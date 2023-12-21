package com.nhnacademy.shoppingmall.cart.repository;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.domain.Cart_Prod;
import java.util.List;
import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findCartById(Integer cartId);

    int save(Cart cart);

    int deleteByCartId(Integer cartId);

    int update(Cart cart);

    int countByCartId(Integer cartId);

    List<Cart> findCartByUserId(String  userId);
    List<Cart> findAll();
    List<Cart_Prod> findCart_Prod(String userId);

    Integer findCartByProductId(Integer productId);

}
