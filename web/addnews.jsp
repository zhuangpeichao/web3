<%@page import="com.bean.Info"%>
<%@page import="com.bean.User"%>
<%@page import="com.bean.Type"%>
<%@page import="com.dao.impl.TypeDaoImpl"%>
<%@page import="com.dao.TypeDao"%>
<%@ page import="java.util.*" pageEncoding="UTF-8"%>




<!DOCTYPE HTML>
<html>
  <head>
    <title>添加</title>
	<link href="css/bootstrap.min.css">
      <script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
  </head>
  <body>
  <%
  TypeDao typedao=new TypeDaoImpl();
  List<Type> list=typedao.queryAll();
 
  
   %>
   <div style="width: 50%;margin: 0 auto;">
   
   <form class="form-horizontal" action="doadd" method="post"  enctype="multipart/form-data" >
    <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">封面图</label>
    <div class="col-sm-10" >
      <input type="file" class="form-control"  name="upload"  value="<%=request.getParameter("newsTitle")==null?"":request.getParameter("newsTitle")%>">
  
    </div>
  </div>
   
   
   
   
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">新闻标题</label>
    <div class="col-sm-10" >
      <input type="text" class="form-control" id="newsTitle" name="newsTitle"   placeholder="请输入标题"  value="<%=request.getAttribute("Info")==null?"":((Info)request.getAttribute("Info")).getNewsTitle()%>">
  
      <span id="inputSuccess2Status" class="sr-only">(success)</span>
      <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" style="padding-right:20px"></span>
  	  
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">新闻类型</label>
    <div class="col-sm-10" >
    
     <select name="newsType" id="newsType" class="form-control" value="<%=request.getAttribute("Info")==null?"":((Info)request.getAttribute("Info")).getNewsType()%>">
     
  	<% for (int i = 0; i < list.size(); i++) {
		%><option value="<%=list.get(i).getTypeId()%>" ><%=list.get(i).getTypeName()%></option><%
	}%>
  	</select>
      
    </div>
  </div>
   
 <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">新闻内容</label>
    <div class="col-sm-10">
    <textarea   class="form-control" rows="10" cols="50" id="newsContent" name="newsContent"placeholder="请输入内容" value="<%=request.getAttribute("Info")==null?"":((Info)request.getAttribute("Info")).getNewsContent()%>"></textarea>
    
    </div>
  </div>
  
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">发布人</label>
    <div class="col-sm-10">
       <input type="text" class="form-control" id = "sendUser"  name="sendUser" disabled="disabled" value="<%=((User)session.getAttribute("login"))%>">
    </div>
  </div>
 
 <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">发布时间</label>
    <div class="col-sm-10">
      <input type="dateFmt" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日 HH时mm分ss秒'})"   class="form-control" id="sendTime" name="sendTime" value="<%=request.getAttribute("Info")==null?"":((Info)request.getAttribute("Info")).getSendTime()%>" >
      <button  type="button" value="返回" id="back">返回</button>
       <input type="submit" value="保存">
    </div>
    
  </div>
  <h4 style="color: red"><%=request.getAttribute("error")!=null?request.getAttribute("error"):""%></h4>
       <h4 style="color: #ff0000">${error!=null?error:""}</h4>
</form>
  </div>
  <script type="text/javascript" src="jq/jquery-3.5.1.min.js"></script>
  <script type="text/javascript">
  $("#back").click(function(){
  history.back("MyJsp.jsp");
  
  });
  </script>
  <script type="text/javascript">
  $(function(){
  $("#")
  
  
  });
  
  
  
  
  
  
  
  
  </script>
  </body>
</html>
