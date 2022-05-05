<%@ page import="com.dao.impl.InfoDaoImpl" %>
<%@ page import="com.bean.Info" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>类别管理</title>
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

<div class="left">
    <ul>
        <li>新闻管理 </li>
        <li>类别管理</li>
        <li>用户管理</li>
    </ul>
</div>
<div class="right">
    <nav style="margin: 0px auto;" class="navbar navbar-default"><a style="line-height: 50px" href="addnews.jsp">添加</a></nav>
    <table class="table table-striped">
        <tr >
            <td>类别Id</td>
            <td>新闻类别</td>
            <td>编辑</td>
        </tr>



    </table>
</div>
</body>
</html>
