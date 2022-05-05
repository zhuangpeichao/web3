package com.testservlet;

import com.bean.User;
import com.service.impl.ServiceUserImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet_login extends HttpServlet {
    ServiceUserImpl user1=new ServiceUserImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTf-8");
        String user= request.getParameter("user");
        String pass= request.getParameter("pass");
        String remember = request.getParameter("remember");



       User u= user1.queryByNames(user,pass);


        if(u!=null&& user.equals(u.getUserName()) && pass.equals(u.getUserPwd())){
           // session.setAttribute("login",u);
            request.getSession().setAttribute("login",u);
            Cookie c1 =null;
            Cookie c2 = null;
            c1=  new Cookie("user1",user);
            response.addCookie(c1);
            response.getWriter().write("success");


            if(remember!=null){
                c2= new Cookie("pass1",pass);


                response.addCookie(c2);


            }else{
                c1=  new Cookie("user1","");
                c2= new Cookie("pass1","");
                response.addCookie(c1);
                response.addCookie(c2);


            }

        }
    }
}
