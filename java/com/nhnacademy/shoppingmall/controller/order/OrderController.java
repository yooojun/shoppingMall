package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.cart.domain.Cart_Prod;
import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/login/order.do")
public class OrderController implements BaseController {
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    CartService cartService = new CartServiceImpl(new CartRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (Objects.isNull(session) || Objects.isNull(user))
            return "shop/login/login_form";

        String modelName = req.getParameter("name");

        log.debug("name :{}", req.getParameter("name"));
        if (Objects.isNull(modelName)) {
            List<Cart_Prod> cartProdList = cartService.findCart_Prod(user.getUserId());
            req.setAttribute("cartProd", cartProdList);
            session.setAttribute("cartProd", cartProdList);
        } else {
            List<Cart_Prod> cartProds = new ArrayList<>();
            Product product = productService.getProductByModelName(modelName);
            Integer quantity = Integer.valueOf(req.getParameter("quantity"));
            cartProds.add(new Cart_Prod(1, product.getProductImage(), product.getModelName(), product.getUnitCost(),quantity));
            req.setAttribute("cartProd", cartProds);
            session.setAttribute("cartProd", cartProds);
        }

        return "shop/order/order";
    }
}
