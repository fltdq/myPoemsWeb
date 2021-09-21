<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的诗词小站</title>
    <%--静态包含base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            //给添加收藏绑定单击事件
            $("button.addToCollect").click(function (){
                var poemId = $(this).attr("poemId");

                //发ajax请求，添加诗词到收藏夹
                $.getJSON("http://localhost:8080/myPoemsWeb/collectServlet","action=ajaxAddItem&id=" + poemId,function (data){
                    $("#collectTotalCount").text("您的收藏夹中有" + data.totalCount + "首作品");
                    $("#collectLastName").text(data.lastName);
                })
            })
        })
    </script>
</head>
<body>
    <div id="header">
        <img class="logo_img" alt="" src="static/img/logo.jpg">
        <span class="wel_word">我的诗词小站</span>
        <div id="index_header">
            <%--如果用户还没有登录，显示【登录和注册的菜单】--%>
                <c:if test="${empty sessionScope.user}">
                    <a href="pages/user/login.jsp">登录</a>
                    <a href="pages/user/regist.jsp">注册</a>
                </c:if>
            <%--如果已经登录，则显示登录成功以后的用户信息--%>
                <c:if test="${not empty sessionScope.user}">
                    <div class="login_user">
                        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临诗词小站</span>
                        <a href="pages/collect/collect.jsp">我的收藏</a>
                        <a href="userServlet?action=loginout">注销</a>
                        <a href="pages/manager/manager.jsp">后台管理</a>
                    </div>
                </c:if>

        </div>
    </div>

    <div id="main">
        <div id="poem">
            <div class="poem_cond">
                <form action="client/clientPoemServlet" method="get">
                    <input type="hidden" name="action" value="pageByAuthor">
                    作者：<input id="author" type="text" name="author" value="${param.author}">
                         <input type="submit" value="查询" />
                </form>
            </div>
            <div style="text-align: center">
                <c:if test="${empty sessionScope.collect.items}">
                    <%--收藏夹为空的输出--%>
                    <span id="collectTotalCount"></span>
                    <div>
                        <span style="color: violet" id="collectEmpty">当前收藏夹为空</span>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.collect.items}">
                    <%--收藏夹非空的输出--%>
                    <span id="collectTotalCount">您的收藏夹中有${sessionScope.collect.totalCount}首作品</span>
                    <div>
                        您刚刚将<span style="color:violet" id="collectLastName">${sessionScope.lastName}</span>加入到了收藏夹中 
                    </div>
                </c:if>
            </div>
            
            <c:forEach items="${requestScope.page.items}" var="poem">
                <div class="p_list">
                    <div class="poem_info">
                        <div class="poem_name">
                            <span class="sp">${poem.name}</span>
                        </div>
                        <div class="poem_dynasty_author">
                            <span class="span">${poem.dynasty}·${poem.author}</span>
                        </div>
                        <div class="poem_first_second">
                            <span class="span">${poem.first}，${poem.second}。</span>
                        </div>
                        <div class="poem_third_fourth">
                            <span class="span">${poem.third}，${poem.fourth}。</span>
                        </div>
                        <c:if test="${not empty poem.fifth}">
                            <div class="poem_fifth_sixth">
                                <span class="span">${poem.fifth}，${poem.sixth}。</span>
                            </div>
                         <%--   <div class="poem_seventh_eighth">
                                <span class="span">${poem.seventh}，${poem.eighth}。</span>
                            </div>--%>
                        </c:if>
                        <c:if test="${not empty poem.seventh}">
                            <div class="poem_seventh_eighth">
                                <span class="span">${poem.seventh}，${poem.eighth}。</span>
                            </div>
                        </c:if>
                        <div class="poem_add">
                            <button poemId="${poem.id}" class="addToCollect">加入收藏夹</button>
                        </div>
                    </div>

                    <%--<div class="poem_info">
                        <div class="poem_name">
                            <span class="sp1">题目：</span>
                            <span class="sp2">${poem.name}</span>
                        </div>
                        <div class="poem_dynasty">
                            <span class="sp1">朝代：</span>
                            <span class="sp2">${poem.dynasty}</span>
                        </div>
                        <div class="poem_author">
                            <span class="sp1">作者：</span>
                            <span class="sp2">${poem.author}</span>
                        </div>
                        <div class="poem_first">
                            <span class="sp1">${poem.first}</span>
                        </div>
                        <div class="poem_second">
                            <span class="sp1">${poem.second}</span>
                        </div>
                        <div class="poem_third">
                            <span class="sp1">${poem.third}</span>
                        </div>
                        <div class="poem_fourth">
                            <span class="sp1">${poem.fourth}</span>
                        </div>
                        <div class="poem_add">
                            <button bookId="${poem.id}" class="addToCollect">加入收藏夹</button>
                        </div>
                    </div>--%>
                </div>
            </c:forEach>

        </div>
        <%--静态包含分页条--%>
        <%@include file="/pages/common/page_nav.jsp"%>
    </div>


   <%--静态包含页脚内容--%>
    <%@include file="/pages/common/footer.jsp"%>


</body>
</html>
