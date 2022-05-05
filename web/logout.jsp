<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<% /*session.invalidate();*/
session.removeAttribute("login");
response.sendRedirect("login.jsp");

 %>
