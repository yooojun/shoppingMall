package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        DbConnectionThreadLocal.initialize();
        if(Objects.isNull(userService.getUser("admin"))){
            userService.saveUser(new User("admin",
                    "admin",
                    "12345",
                    "1111-1-1",
                    User.Auth.ROLE_ADMIN,
                    1000000,
                    LocalDateTime.now(),
                    LocalDateTime.now()));
        }

        if(Objects.isNull(userService.getUser("user"))){
            userService.saveUser(new User("user",
                    "user",
                    "12345",
                    "1111-1-1",
                    User.Auth.ROLE_USER,
                    1000000,
                    LocalDateTime.now(),
                    LocalDateTime.now()));
        }

//        if(Objects.isNull(productService.getProductByModelName("test_nhn"))){
//            productService.saveProduct(new Product(
//                    "test_nhn",
//                    "nhn",
//                    10000
//            ));
//        }

        DbConnectionThreadLocal.reset();
    }
}
