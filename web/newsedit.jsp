<%@page import="com.dao.InfoDao"%>
<%@page import="com.bean.Info"%>
<%@page import="com.dao.impl.InfoDaoImpl"%>
<%@page import="com.bean.Type"%>
<%@page import="com.dao.impl.TypeDaoImpl"%>
<%@page import="com.dao.TypeDao"%>
<%@ page import="java.util.*" pageEncoding="UTF-8"%>




<!DOCTYPE HTML>
<html>
  <head>
    <title>编辑</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
  </head>
  <%
  String idstr=request.getParameter("id");
  
int id=0;

try {
		id=Integer.parseInt(idstr);
	} catch (Exception e) {
		// TODO: handle exception
		id=0;
	}
	
	InfoDaoImpl info=new InfoDaoImpl();
	Info obj = info.select(id);
	/*  System.out.println(idstr);
	 System.out.println(obj.getNewsTitle()); */
	 
   %>

  <body>
  <%
  TypeDao typedao=new TypeDaoImpl();
  List<Type> list=typedao.queryAll();
   %>
   <div style="width: 50%;margin: 0 auto;">
   
   <form class="form-horizontal" action="edit" method="get" id="for">
   <input type="hidden" name="idd" value="<%=request.getParameter("id")%>">
   
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">新闻标题</label>
    <div class="col-sm-10" >
      <input type="text" class="form-control" id="newsTitle" name="newsTitle"  placeholder="编辑标题" value="<%=request.getParameter("newsTitle")==null?obj.getNewsTitle():request.getParameter("newsTitle")%>">
    
      <span id="inputSuccess2Status" class="sr-only">(success)</span>
      <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="padding-right:20px"></span>
  	  
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">新闻类型</label>
    <div class="col-sm-10" >
    
     <select name="newsType" id="newsType" class="form-control" value="<%=request.getParameter("newsType")==null?obj.getNewsType():request.getParameter("newsType")%>">
     
  	<% for (int i = 0; i < list.size(); i++) {
		%><option ><%=list.get(i).getTypeName()%></option><%
	}%>
  	</select>
      
    </div>
  </div>
  
 <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-l】abel">新闻内容</label>
    <div class="col-sm-10">
    <textarea   class="form-control" rows="10" cols="50" id="newsContent" name="newsContent"placeholder="编辑内容"><%=obj.getNewsContent()%></textarea>
    
    </div>
  </div>
  
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">发布人</label>
    <div class="col-sm-10">
       <input type="text" class="form-control" id = "senduser" name="sendUser" disabled="disabled" value="<%=session.getAttribute("login")%>"  >
    </div>
  </div>
 
 <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">发布时间</label>
    <div class="col-sm-10">
      <input type="date" class="form-control" id="sendTime" name="sendTime" value="<%=request.getParameter("sendtime")==null?obj.getSendTime():request.getParameter("sendtime") %>" >
      <button  type="button" value="返回" id="back">返回</button>
       <input type="submit" value="保存">
    </div>
    
  </div>

</form>
  </div>
  <script type="text/javascript" src="jq/jquery-3.5.1.min.js"></script>
  <script type="text/javascript">
  $("#back").click(function(){
  history.back("MyJsp.jsp");
  
  });
  </script>
  <script type="text/javascript">

  
  
  
  
  
  
  
  
  </script>
  </body>
</html>
