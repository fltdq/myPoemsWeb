<%--
  Created by IntelliJ IDEA.
  User: 陈思航
  Date: 2021/1/2
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的诗词小站会员登录页面</title>
    <%--写base标签，永远固定相对路径跳转的结果--%>
    <%--静态包含base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
        <div id="header">
            <img class="logo_img" alt="" src="static/img/logo.jpg">

            <%--静态包含登录成功之后的菜单--%>
            <%@include file="/pages/common/login_success_menu.jsp"%>
        </div>

        <div id="main">

            <h1>欢迎回来 <a href="index.jsp">转到主页</a> </h1>

        </div>
</body>
</html>
