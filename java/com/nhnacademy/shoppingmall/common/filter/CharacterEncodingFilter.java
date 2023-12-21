package com.nhnacademy.shoppingmall.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        filterName = "characterEncodingFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8")
        }
)
public class CharacterEncodingFilter  implements Filter {

    @Override

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //todo#8 UTF-8 인코딩, initParams의 encoding parameter value값을 charset 으로 지정합니다.
        //@WebFilter(initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html");

        filterChain.doFilter(servletRequest,servletResponse);
    }

}
