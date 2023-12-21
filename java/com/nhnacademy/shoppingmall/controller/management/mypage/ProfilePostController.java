package com.nhnacademy.shoppingmall.controller.management.mypage;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST,value = "/mypage/profileAction.do")
public class ProfilePostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User oldUser = (User) session.getAttribute("user");

        String userId = oldUser.getUserId();
        String userName = req.getParameter("user_name");
        String userPassword = req.getParameter("user_password");
        String userBirth = req.getParameter("user_birth").replaceAll("-", "");
        User user = null;
        // 2023-12-03
        // varchar(8)
        try {
            user = new User(userId,userName ,userPassword, userBirth, User.Auth.ROLE_USER);
            userService.updateUser(user);
        } catch (UserNotFoundException e){
            return "shop/signup/signup_form";
        }

        session.setAttribute("user", user);
        return "redirect:/mypage.do";
    }
}
