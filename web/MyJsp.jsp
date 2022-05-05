
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="com.dao.impl.*" %>
<%@ page import="com.bean.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
<title>jsp练习</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
<style>
* {
	margin: 0;
	padding: 0;
}

.left {
	list-style: none;
	width: 200px;
	padding: 10px;
	background-color: rgba(204, 51, 204, 0.2);
	float: left;
}

.right {
	width: 90% ;

	height: 600px;
	float: left;
	padding: 10px
}
</style>
</head>
<body>




	<h3>
		Hello,${login.userName}<%=((User)session.getAttribute("login")).getUserName()%></h3>
	<h3>
		<a href="logout.jsp">退出登录</a>
	</h3>
	<div class="left">
		<ul>
			<li>新闻管理 </li>
			<li><a href="LeiBie.jsp">类别管理</a></li>
			<li>用户管理</li>
		</ul>

	</div>
	<div>
		<form class="form-inline">
			<div class="form-group">
				<label for="select">类型</label>


				<select id="select"type="email" class="form-control" >
					<option></option>

				</select>
			</div>
			<div class="form-group">
				<label for="keywords" >标题</label>
				<input type="email" class="form-control"  placeholder="请输入标题" id="keywords">
			</div>
			<button type="button" id="serch" >搜索</button>
		</form>



	</div>
	<div class="right">
	<nav style="margin: 0px auto;" class="navbar navbar-default"><a style="line-height: 50px" href="addnews.jsp">添加</a></nav>
		<table id="table" class="table table-striped">

			<tr >
				<td>Id</td>
				<td>标题</td>
				<td>类别</td>
				<td>发布人</td>
				<td>发布时间</td>
				<td>编辑</td>
			</tr>

	
<%--	<%  List<Info> list = (List<Info>) request.getAttribute("list");
	/*int page1= (int) request.getAttribute("page1");*/


 %>--%>

		</table>
			 <ul class="pagination">
    <li>
      <a href="MyJsp.jsp?page=1" aria-label="Previous">
        <span aria-hidden="true" id="first">&laquo;</span>
      </a>
    </li>
    <li><a id="pre" <%--href="myjsp?page=<%=(page1-1)<1?page1:(page1-1)%>"--%>>上一页</a></li>
    <li><a href="#" id="yeshu"><%--第页/共${size}页--%></a></li>
    <li><a id="next" <%--href="myjsp?page=<%=page1+1>4?page1:(page1+1)%>"--%> >下一页</a></li>
    <li>
      <a href="MyJsp.jsp?page=4" aria-label="Next">
        <span aria-hidden="true" id="lastq">&raquo;</span>
      </a>
    </li>
  </ul>
	</div>
<script type="text/javascript" src="jq/jquery-3.5.1.min.js"></script>
<script>

	//下拉列表
	$.ajax({
		url:"type",
		type:"get",
		data:{},
		dataType:"json",
		success:function (res){
			for (let i = 0; i <res.length ; i++) {
				$("#select").append(

				"<option>"+res[i].newstype+"</option>"

				)
			}
		}

	})






	var size=1;
	var page1=1;
	fn(1)
function fn(page1){
	$.ajax({
		url:"myjsp",
		type:"get",
		data:{
			page1:page1,
			typestr:$("#select").val(),
			keywords:$("#keywords").val(),

		},
		dataType:"json",
		success:function (res){
			$("#table tr:eq(0)").siblings().remove();
			for (let i = 0; i <res.length-1; i++) {
				$("#table").append(
						"<tr> <td>"+res[i].newsId+
						"</td> <td>"+res[i].newsTitle+
						"</td> <td>"+res[i].newsType+
						"</td> <td>"+res[i].sendUser+
						"</td> <td>"+res[i].sendTime+
						"</td><td>编辑</td> <td>删除</td></tr>"
				)
			}
			size=res[res.length-1].size;

			page1=res[res.length-1].page1;
			$("#yeshu").text("第"+page1+"页"+"/"+"共"+size+"页")
			$("tr td:last-child").on("click",function (){
				/*var id=$(this).attr("id");*/
				var tr=$(this).parent();

				var id= $(this).parent().children("td:first-child").text();
				$.ajax({
					url:"delete",
					type:"get",
					data:{"id":id},
					dataType:"text",
					success:function (res){
						if (res=="success"){
							alert("删除成功");
							tr.remove();
						}else{
							alert("删除失败");
						}
					}
				})
			})
		}
	})
}

//下一页
	$(function (){
$("#next").click(function (){

	page1++;
if (page1>size){
page1=size;
}else{
	fn(page1);
}
})
	})
//上一页
	$(function (){
		$("#pre").click(function (){

			page1--;
			if (page1<1){
				page1=1;
			}else{
				fn(page1);
			}



		})
	})

//首页
	$(function (){
		$("#first").click(function (){
			fn(1)

		})
	})

//尾页
	$(function (){
		$("#lastq").click(function (){
			alert(size)
			alert(page1)
			fn(size)

		})


	})
	$("#serch").on("click",function (){

		fn(1);


	})

</script>
 

</body>
</html>
