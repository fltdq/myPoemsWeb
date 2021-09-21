<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>诗词管理</title>
    <%--静态包含base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            //给删除的a标签绑定单击事件，用于删除的确认提示操作
            $("a.deleteClass").click(function (){
                //在事件的function函数中，有一个this对象，这个this对象，是当前正在响应的dom对象
                /**
                 *confirm是确认提示框函数
                 * 参数是它的提示内容
                 * 它有两个按钮，一个是确认，一个是取消
                 * 返回true表示点击了，确认，返回false表示单击取消
                 */
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】");
                //return false//阻止元素的默认行为===不提交请求

            })
        })
    </script>
</head>
<body>
    <div id="header">
        <img class="logo_img" alt="" src="static/img/logo.jpg">
        <span class="wel_word">诗词管理系统</span>

        <%--静态包含manager管理模块的菜单--%>
        <%@include file="/pages/common/manager_menu.jsp"%>
    </div>

    <div id="main">
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

            <c:forEach items="${requestScope.page.items}" var="poem">
                <tr>
                    <td>${poem.name}</td>
                    <td>${poem.dynasty}</td>
                    <td>${poem.author}</td>
                    <td>${poem.first}</td>
                    <td>${poem.second}</td>
                    <td>${poem.third}</td>
                    <td>${poem.fourth}</td>
                    <td>${poem.fifth}</td>
                    <td>${poem.sixth}</td>
                    <td>${poem.seventh}</td>
                    <td>${poem.eighth}</td>
                    <td><a href="manager/poemServlet?action=getPoem&id=${poem.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                    <td><a class="deleteClass" href="manager/poemServlet?action=delete&id=${poem.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
                </tr>
            </c:forEach>

            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><a href="pages/manager/poem_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加诗词</a> </td>
            </tr>
        </table>

        <%--静态包含分页条--%>
        <%@include file="/pages/common/page_nav.jsp"%>
    </div>

    <%--静态包含页脚内容--%>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
