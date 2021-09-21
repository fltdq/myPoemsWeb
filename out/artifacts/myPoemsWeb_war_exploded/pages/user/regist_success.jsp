<%--
  Created by IntelliJ IDEA.
  User: 陈思航
  Date: 2021/1/3
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的诗词小站注册页面</title>
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
            <span class="wel_word"></span>

            <%--静态包含，登录 成功之后的菜单--%>
            <%@include file="/pages/common/login_success_menu.jsp"%>
        </div>

        <div id="main">
            <h1>注册成功！<a href="../../index.jsp">转到主页</a></h1>
        </div>

        <%--静态包含页脚内容--%>
        <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
