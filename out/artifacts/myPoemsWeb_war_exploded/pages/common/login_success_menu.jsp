<%--
  Created by IntelliJ IDEA.
  User: 陈思航
  Date: 2021/1/2
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="login_success_menu">
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临我的诗词小站</span>
    <a href="pages/collect/collect.jsp">我的收藏夹</a>
    <a href="userServlet?action=loginout">注销</a>
    <a href="index.jsp">返回</a>
</div>