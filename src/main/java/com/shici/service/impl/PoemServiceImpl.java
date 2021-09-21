package com.shici.service.impl;

import com.shici.dao.PoemDao;
import com.shici.dao.impl.PoemDaoImpl;
import com.shici.pojo.Page;
import com.shici.pojo.Poem;
import com.shici.service.PoemService;

import java.sql.SQLException;
import java.util.List;

/**
 * FileName: PoemServiceImpl
 * Description:
 * Author: CSH
 * Date: 2021/1/4 18:37
 * Version: 1.0
 */
public class PoemServiceImpl implements PoemService {

    private PoemDao poemDao = new PoemDaoImpl();

    @Override
    public void addPoem(Poem poem) {
        poemDao.addPoem(poem);
    }

    @Override
    public void deletePoemById(Integer id) {
        poemDao.deletePoemById(id);
    }

    @Override
    public void updatePoem(Poem poem) {
        poemDao.updatePoem(poem);
    }

    @Override
    public Poem queryPoemById(Integer id) {
        return poemDao.queryPoemById(id);
    }

    @Override
    public List<Poem> queryPoems() throws SQLException {
        return poemDao.queryPoems();
    }

    @Override
    public Page<Poem> page(int pageNo, int pageSize) throws SQLException {
        Page<Poem> page = new Page<Poem>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = poemDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //设置当前页吗
        page.setPageNo(pageNo);

        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Poem> items = poemDao.queryForPageItems(begin,pageSize);
       //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Poem> pageByAuthor(int pageNo, int pageSize, String author) throws SQLException {
        Page<Poem> page = new Page<Poem>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = poemDao.queryForPageTotalCountByAuthor(author);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);

        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Poem> items = poemDao.queryForPageItemsByAuthor(begin,pageSize,author);
        //设置当前页数据
        page.setItems(items);

        return page;
    }
}
