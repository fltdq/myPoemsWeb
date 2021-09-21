package com.shici.web;

import com.shici.pojo.Page;
import com.shici.pojo.Poem;
import com.shici.service.PoemService;
import com.shici.service.impl.PoemServiceImpl;
import com.shici.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * FileName: PoemServlet
 * Description:
 * Author: CSH
 * Date: 2021/1/4 19:20
 * Version: 1.0
 */
public class PoemServlet extends BaseServlet{
    private PoemService poemService = new PoemServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //1、获取请求的参数pageNO和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        //2、调用PoemService.page(pageNo,pageSize):Page对象
        Page<Poem> page = poemService.page(pageNo,pageSize);

        page.setUrl("manager/poemServlet?action=page");

        //3、保存request对象到Request域中
        req.setAttribute("page",page);
        //4、请求转发到pages/manager/poem_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/poem_manager.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //1、获取请求的参数
        Poem poem = WebUtils.copyParamToBean(req.getParameterMap(), new Poem());
        //2、调用PoemService.addPoem()保存图书
        poemService.addPoem(poem);
        //3、跳到诗词列表页面
        //req.getRequestDispatcher("/manager/poemServlet?action=list").forward(req,resp);
        /*采用请求重定向方法，在请求转发中/斜杠代表到工程路径,
        * 映射到web目录，而请求重定向/代表端口号，需要加上工程路径
        * */
        resp.sendRedirect(req.getContextPath() + "/manager/poemServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1、获取请求的参数id
        int id = WebUtils.parseInt(req.getParameter("id"),0);

        //2、调用poemService.deletePoemById();删除作品
        poemService.deletePoemById(id);

        //3、请求重定向
        resp.sendRedirect(req.getContextPath() + "/manager/poemServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数==封装成poem对象
        Poem poem = WebUtils.copyParamToBean(req.getParameterMap(),new Poem());
        //2、调用PoemService.update(poem);修改诗词
        poemService.updatePoem(poem);
        //3、重定向回诗词列表管理页面
        //地址：/工程名/manager/poemServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/poemServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void getPoem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的诗词作品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2、调用poemService.queryPoemById查询诗词
        Poem poem = poemService.queryPoemById(id);
        //3、保存图书到request域中
        req.setAttribute("poem",poem);
        //4、请求转发到pages/manager/poem_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/poem_edit.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //1、通过PoemService查询全部图书
        List<Poem> poems = poemService.queryPoems();
        //2、把全部诗词保存到Request域当中
        req.setAttribute("poems",poems);
        //3、请求转发到/pages/manager/poem_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/poem_manager.jsp").forward(req,resp);
    }
}
