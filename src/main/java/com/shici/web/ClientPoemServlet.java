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

/**
 * FileName: clientServlet
 * Description:
 * Author: CSH
 * Date: 2020/12/21 11:24
 * Version: 1.0
 */
public class ClientPoemServlet extends PoemServlet {

    private PoemService poemService = new PoemServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //1、获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用PoemService.page(pageNo,pageSize):Page对象
        Page<Poem> page = poemService.page(pageNo,pageSize);

        page.setUrl("client/clientPoemServlet?action=page");

        //3、保存Page对象到Request域中
        req.setAttribute("page",page);
        //4、请求转发到pages/manager/poem_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //1、获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        String author = req.getParameter("author");
        //2、调用PoemService.page(pageNo,pageSize):Page对象
        Page<Poem> page = poemService.pageByAuthor(pageNo,pageSize,author);

        StringBuilder sb = new StringBuilder("client/clientPoemServlet?action=pageByAuthor");
        //如果有作者参数，追加到分页条的地址参数中
        if (req.getParameter("author") != null){
            sb.append("&author=").append(req.getParameter("author"));
        }
        page.setUrl(sb.toString());

        //3、保存Page对象到Request域中
        req.setAttribute("page",page);
        //4、请求转发到pages/manager/poem_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
