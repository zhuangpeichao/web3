<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
  <head>
    <title>登录</title>
	
  </head>
  <body>
<%-- <% 


 int count=0;
			if(application.getAttribute("count")!=null){
			count=(Integer)application.getAttribute("count");
			count++;  
			}else{
			 count = 1;}
          application.setAttribute("count",count);
%>
<%=count%>   --%>

 <%-- <h1>sessionID  <%=session.getId()%></h1>
 <h1>最大失效时间  <%=session.getMaxInactiveInterval() %></h1> --%>
 

 
 
 
  <form >
  <%
String user="";
  	String pass="";
Cookie [] cookies = request.getCookies();
     	 if(cookies!=null){
     	 	//遍历cookie
     	 	for(int i = 0;i<cookies.length;i++){
     	 		if(cookies[i].getName().equals("user1")){
     	 			//获取cookie里面的用户名
     	 			user = cookies[i].getValue();
     	 		}else if(cookies[i].getName().equals("pass1")){
     	 			//获取密码
     	 			pass = cookies[i].getValue();
     	 		}
     	 	}
     	 }
 %> 
  
  <table>
  
  	<tr>
  	<td >账号</td>
  	<td><input type="text" name="user" id="userName" value="<%=request.getParameter("user")==null?user:request.getParameter("user")%>"></td>
  	</tr>
  <tr>
  	<td >密码</td>
  	<td><input type="password" id="userPwd" name="pass" value="<%=request.getParameter("pass")==null?pass:""%>"></td>
  	</tr>
  </table>
   <input type="checkbox" name="remember"/>记住密码 
  <input type="button" id="butt" value="登录">
  <h1><%=request.getAttribute("error")!=null?request.getAttribute("error"):""%></h1>
	  <h1>${error!=null?error:""}</h1>
  </form>
<script type="text/javascript" src="jq/jquery-3.5.1.min.js"></script>
<script>
	$(function (){
		$("#butt").click(function (){

			if ($("#userName").val()==""){
				$("#userName").focus();
				return;
			}
			if ($("#userPwd").val()=="") {
				$("#userPwd").focus();

			}

			$.ajax({
				url:"login",
				type:"post",
				data:{

					"user":$("#userName").val(),
					"pass":$("#userPwd").val()
				},
				dataType:"text",
				success:function (res) {
					if (res=="success"){
						location.href="MyJsp.jsp"
					}
				}
			})
		})
	})
</script>
  
  </body>
</html>
