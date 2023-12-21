package com.nhnacademy.shoppingmall.controller.order;


import com.nhnacademy.shoppingmall.cart.domain.Cart_Prod;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.orderdetail.domain.OrderDetails;
import com.nhnacademy.shoppingmall.orderdetail.repository.impl.OrderDetailRepositoryImpl;
import com.nhnacademy.shoppingmall.orderdetail.service.OrderDetailService;
import com.nhnacademy.shoppingmall.orderdetail.service.impl.OrderDetailServiceImpl;
import com.nhnacademy.shoppingmall.orders.domain.Orders;
import com.nhnacademy.shoppingmall.orders.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.orders.service.OrderService;
import com.nhnacademy.shoppingmall.orders.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/login/order/orderAction.do")
public class OrderPostController implements BaseController {
    OrderDetailService orderDetailService = new OrderDetailServiceImpl(new OrderDetailRepositoryImpl());
    OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (Objects.isNull(session) || Objects.isNull(user)) {
            return "shop/login/login_form";
        }
        List<Cart_Prod> cartProdList = (List<Cart_Prod>) session.getAttribute("cartProd");

        try {
            Orders order =new Orders(0, user.getUserId(), LocalDateTime.now(), LocalDateTime.now().plusDays(7l));
            orderService.save(order);
            for (Cart_Prod cartProd : cartProdList) {
                log.debug("model = {}", cartProd.getModelName());
                Product product = productService.getProductByModelName(cartProd.getModelName());
                user.setUserPoint(user.getUserPoint() - (product.getUnitCost() * cartProd.getQuantity()));
                if(user.getUserPoint() < 0){
                    log.debug("잔액 : {}", user.getUserPoint());
                    return "/error.do";
                }
                orderDetailService.save(new OrderDetails(order.getOrderId(),
                        product.getProductId(),
                        cartProd.getQuantity(),
                        cartProd.getUnitCost()));
            }
        } catch (RuntimeException e) {
            return "/error.do";
        }
        userService.updateUser(user);
        return "redirect:/index.do";
    }
}
