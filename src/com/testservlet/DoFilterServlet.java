package com.testservlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoFilterServlet implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(">>>>>>初始化");


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//检查session标识是否登录
        //登录验证
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
       /* if (uri1.startsWith("/login")) {
            filterChain.doFilter(request, response);
        }else {
            response.sendRedirect("login.jsp");
        }*/
        Object obj = request.getSession().getAttribute("login");
        System.out.println(obj);
        if (obj!= null) {
            filterChain.doFilter(request, response);
        } else {
            System.out.println("1111");
            String uri1 = request.getRequestURI();


            String[] co = new String[]{
                    "/login", "/css/", "/js/","/jq/"
            };
            boolean uri2 = false;
            for (int i = 0; i < co.length; i++) {

                if (uri1.startsWith(co[i])) {

                    filterChain.doFilter(request, response);

                    uri2 = true;
                     break;
                }
            }
            if (uri2 == false) {
                response.sendRedirect("login.jsp");
            }
        }


    }

    @Override
    public void destroy() {
        System.out.println(">>>>>>销毁");
    }
}
