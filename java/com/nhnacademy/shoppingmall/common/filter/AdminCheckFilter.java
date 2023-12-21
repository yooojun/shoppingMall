package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(
        filterName = "adminCheckFilter",
        urlPatterns = {"/admin/*", "/admin.do"}
)
public class AdminCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리
        HttpSession session = req.getSession(false);
        if (Objects.isNull(session)) {
            res.sendRedirect("/login.do");
        }
        User user = (User) session.getAttribute("user");
        if (user.getUserAuth().equals(User.Auth.ROLE_USER)) {
            res.sendRedirect("/error.do");
        }
        chain.doFilter(req, res);
    }
}
