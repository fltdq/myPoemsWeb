<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的收藏夹</title>
    <%-- 静态包含base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.jpg">
    <span class="wel_word">我的收藏夹</span>

    <%--静态包含登录成功之后的菜单--%>
    <%@include file="/pages/common/login_success_menu.jsp"%>
    <script type="text/javascript">
        //给【删除】绑定单击事件
        $(function (){
            $("a.deleteItem").click(function (){
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text()+"】吗？");

            })
            //给清空收藏夹绑定单击事件
            $("#clearCollect").click(function (){
                return confirm("你确定要清空收藏夹吗?");
            })
        })
    </script>
</div>

<div id="main">
    <table>
        <tr>
            <td>题目</td>
            <td>朝代</td>
            <td>作者</td>
            <td>第一句</td>
            <td>第二句</td>
            <td>第三句</td>
            <td>第四句</td>
            <td>第五句</td>
            <td>第六句</td>
            <td>第七句</td>
            <td>第八句</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty sessionScope.collect.items}">
            <%--如果收藏夹为空的情况--%>
            <tr>
                <td colspan="5"><a href="index.jsp">亲，当前收藏夹为空，快跟小伙伴们去浏览诗词吧！！！</a></td>
            </tr>
        </c:if>
        <c:if test="${not empty sessionScope.collect.items}">
            <%--如果收藏夹非空的情况--%>
            <c:forEach items="${sessionScope.collect.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td>${entry.value.dynasty}</td>
                    <td>${entry.value.author}</td>
                    <td>${entry.value.first}</td>
                    <td>${entry.value.second}</td>
                    <td>${entry.value.third}</td>
                    <td>${entry.value.fourth}</td>
                    <td>${entry.value.fifth}</td>
                    <td>${entry.value.sixth}</td>
                    <td>${entry.value.seventh}</td>
                    <td>${entry.value.eighth}</td>
                    <td><a class="deleteItem" href="collectServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <%--如果收藏夹非空才输出页面的内容--%>
    <c:if test="${not empty sessionScope.collect.items}">
        <div class="collect_info">
            <span class="collect_span">收藏夹中共有<span class="b_count">${sessionScope.collect.totalCount}</span>首作品</span>
            <span class="collect_span"><a id="clearCollect" href="collectServlet?action=clear">清空收藏夹</a></span>
        </div>
    </c:if>
</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>
