package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/login/order/cartAction.do")
public class CartPostController implements BaseController {

    CartService cartService = new CartServiceImpl(new CartRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (Objects.isNull(session) || Objects.isNull(user)) {
            return "shop/login/login_form";
        }
        List<Cart> userCart = cartService.findUserCart(user.getUserId());
        Integer quantity = Integer.valueOf(req.getParameter("quantity"));
        Integer productId = Integer.valueOf(req.getParameter("productId"));
        for (Cart cart : userCart) {
            if(cart.getProductId().equals(productId))
                return "redirect:/login/cart.do";
        }
        cartService.saveCart(new Cart(0, user.getUserId(),
                quantity, productId, LocalDateTime.now()));

        return "redirect:/login/cart.do";
    }
}
