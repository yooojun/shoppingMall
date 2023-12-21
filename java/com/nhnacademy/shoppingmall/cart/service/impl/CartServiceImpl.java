package com.nhnacademy.shoppingmall.cart.service.impl;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.domain.Cart_Prod;
import com.nhnacademy.shoppingmall.cart.exception.CartAlreadyExistsException;
import com.nhnacademy.shoppingmall.cart.exception.CartNotFoundException;
import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CartServiceImpl implements CartService {
    CartRepositoryImpl cartRepository;

    public CartServiceImpl(CartRepositoryImpl cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getCart(Integer cartId) {
        Optional<Cart> cart = cartRepository.findCartById(cartId);
        if (cart.isEmpty())
            return null;
        return cart.get();
    }

    @Override
    public void saveCart(Cart cart) {
        if(cartRepository.countByCartId(cart.getCartId())>0)
            throw new CartAlreadyExistsException(cart.getCartId());
        int result = cartRepository.save(cart);
        if(result < 1){
            throw new RuntimeException("save fail");
        }
    }

    @Override
    public void updateCart(Cart cart) {
        if(cartRepository.countByCartId(cart.getCartId())<1)
            throw new CartNotFoundException(cart.getCartId());
        int result = cartRepository.update(cart);
        if(result < 1){
            throw new RuntimeException("update fail");
        }
    }

    @Override
    public void deleteCart(Integer cartId) {
        if(cartRepository.countByCartId(cartId)<1)
            throw new CartNotFoundException(cartId);
        int result = cartRepository.deleteByCartId(cartId);
        if(result < 1){
            throw new RuntimeException("delete fail");
        }
    }

    @Override
    public List<Cart> findUserCart(String user_id) {
        return cartRepository.findCartByUserId(user_id);
    }

    @Override
    public List<Cart> findAll() {
        List<Cart> cartList = cartRepository.findAll();
        if(cartList.isEmpty()){
            return Collections.emptyList();
        }
        return cartList;
    }

    @Override
    public List<Cart_Prod> findCart_Prod(String userId) {
        List<Cart_Prod> cartProdList = cartRepository.findCart_Prod(userId);
        if(cartProdList.isEmpty()){
            return Collections.emptyList();
        }
        return cartProdList;
    }

    @Override
    public Integer findCartByProductId(Integer productId) {
        Integer count = cartRepository.findCartByProductId(productId);
        return count;
    }


}
