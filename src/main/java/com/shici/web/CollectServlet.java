package com.shici.web;

import com.google.gson.Gson;
import com.shici.pojo.Collect;
import com.shici.pojo.CollectItem;
import com.shici.pojo.Poem;
import com.shici.service.PoemService;
import com.shici.service.impl.PoemServiceImpl;
import com.shici.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: CollectServlet
 * Description:
 * Author: CSH
 * Date: 2021/1/6 21:29
 * Version: 1.0
 */
public class CollectServlet extends BaseServlet{
    private PoemService poemService = new PoemServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用poemService.queryPoemById(id):Poem得到作品的信息
        Poem poem = poemService.queryPoemById(id);
        //把图书信息，转换为CollectItem商品项
        CollectItem collectItem = new CollectItem(poem.getId(),poem.getName(),poem.getDynasty(),poem.getAuthor(),poem.getFirst(),poem.getSecond(),poem.getThird(),poem.getFourth(),
                poem.getFifth(),poem.getSixth(),poem.getSeventh(),poem.getEighth());
        //调用Collect.addItem(CollectItem)；添加作品项
        Collect collect = (Collect) req.getSession().getAttribute("collect");
        if (collect == null){
            collect = new Collect();
            req.getSession().setAttribute("collect",collect);
        }
        collect.addItem(collectItem);

        //最后添加的一个作品名称
        req.getSession().setAttribute("lastName",collectItem.getName());
        //重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用poemService.queryPoemById(id):Poem得到图书的信息
        Poem poem = poemService.queryPoemById(id);
        //把作品信息，转换成为CollectItem作品项
        CollectItem collectItem = new CollectItem(poem.getId(),poem.getName(),poem.getDynasty(),poem.getAuthor(),poem.getFirst(),poem.getSecond(),poem.getThird(),poem.getFourth(),
                poem.getFifth(),poem.getSixth(),poem.getSeventh(),poem.getEighth());
        //调用Collect.addItem(CollectItem)；添加商品项
        Collect collect = (Collect) req.getSession().getAttribute("collect");
        if (collect == null){
            collect = new Collect();
            req.getSession().setAttribute("collect",collect);
        }
        collect.addItem(collectItem);

        //最后一个添加商品的名称
        req.getSession().setAttribute("lastName",collectItem.getName());
        //返回收藏夹中总的作品数量和最后一个添加的作品名称
        Map<String,Object> resultMap = new HashMap<String, Object>();

        resultMap.put("totalCount",collect.getTotalCount());
        resultMap.put("lastName",collectItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);

        resp.getWriter().write(resultMapJsonString);
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取作品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //获取收藏夹对象
        Collect collect = (Collect) req.getSession().getAttribute("collect");
        if(collect != null){
            //删除了收藏夹商品项
            collect.deleteItem(id);
            //重定向回原来收藏夹展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取购物车对象
        Collect collect = (Collect) req.getSession().getAttribute("collect");
        if (collect != null){
            //清空收藏夹
            collect.clear();
            //重定向回原来收藏夹展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
