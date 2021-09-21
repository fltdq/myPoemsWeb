<%--
  Created by IntelliJ IDEA.
  User: 陈思航
  Date: 2021/1/4
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑图书</title>
    <%--静态包含base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp"%>

    <style type="text/css">
        h1{
            text-align: center;
            margin-top: 200px;
        }

        h1 a{
            color: red;
        }

        input{
            text-align: center;
        }
    </style>
</head>
<body>
        <div id = "header">
            <img class="logo_img" alt="" src="static/img/logo.jpg">
            <span class="wel_word">编辑诗词</span>

            <%--静态包含manager管理模块的菜单--%>
            <%@include file="/pages/common/manager_menu.jsp"%>
        </div>

        <div id="main">
            <form action="manager/poemServlet" method="get">
                <input type="hidden" name="pageNo" value="${param.pageNo}">
                <input type="hidden" name="action" value="${ empty param.id ? "add" : "update"}"/>
                <input type="hidden" name="id" value="${requestScope.poem.id}">

                <table>
                    <tr>
                        <td>名称</td>
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
                        <td colspan="2">操作</td>
                    </tr>
                    <tr>
                        <td><input name="name" type="text" value="${requestScope.poem.name}"/> </td>
                        <td><input name="dynasty" type="text" value="${requestScope.poem.dynasty}"/> </td>
                        <td><input name="author" type="text" value="${requestScope.poem.author}"></td>
                        <td><input name="first" type="text" value="${requestScope.poem.first}"></td>
                        <td><input name="second" type="text" value="${requestScope.poem.second}"></td>
                        <td><input name="third" type="text" value="${requestScope.poem.third}"></td>
                        <td><input name="fourth" type="text" value="${requestScope.poem.fourth}"></td>
                        <td><input name="fifth" type="text" value="${requestScope.poem.fifth}"></td>
                        <td><input name="sixth" type="text" value="${requestScope.poem.sixth}"></td>
                        <td><input name="seventh" type="text" value="${requestScope.poem.seventh}"></td>
                        <td><input name="eighth" type="text" value="${requestScope.poem.eighth}"></td>
                        <td><input type="submit" value="提交"></td>
                    </tr>
                </table>
            </form>
        </div>

        <%--静态包含页脚内容--%>
        <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
