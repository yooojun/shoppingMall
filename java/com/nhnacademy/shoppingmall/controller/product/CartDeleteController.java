package com.nhnacademy.shoppingmall.controller.product;


import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/login/cart/deleteAction.do")
public class CartDeleteController implements BaseController {
    CartService cartService = new CartServiceImpl(new CartRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer cartId = Integer.valueOf(req.getParameter("id"));
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (Objects.isNull(session) || Objects.isNull(user)) {
            return "shop/login/login_form";
        }
        List<Cart> userCart = cartService.findUserCart(user.getUserId());
        for (Cart cart : userCart) {
            if(cart.getCartId().equals(cartId))
            {
                cartService.deleteCart(cartId);
            }
        }
        return "redirect:/login/cart.do";
    }
}
