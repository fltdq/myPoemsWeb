package com.shici.dao.impl;

import com.shici.dao.BaseDao;
import com.shici.dao.PoemDao;
import com.shici.pojo.Poem;

import java.sql.SQLException;
import java.util.List;

/**
 * FileName: PoemDaoImpl
 * Description:
 * Author: CSH
 * Date: 2021/1/4 16:11
 * Version: 1.0
 */
public class PoemDaoImpl extends BaseDao implements PoemDao {
    @Override
    public int addPoem(Poem poem) {
        String sql = "insert into t_poems(name,dynasty,author,first,second,third,fourth,fifth,sixth,seventh,eighth) values(?,?,?,?,?,?,?,?,?,?,?)";
        return update(sql,poem.getName(),poem.getDynasty(),poem.getAuthor(),poem.getFirst(),poem.getSecond(),poem.getThird(),poem.getFourth(),poem.getFifth(),poem.getSixth(),poem.getSeventh(),poem.getEighth());
    }



    @Override
    public int deletePoemById(Integer id) {
        String sql = "delete from t_poems where id = ?";
        return update(sql,id);
    }

    @Override
    public int updatePoem(Poem poem) {
        String sql = "update t_poems set name=?,dynasty=?,author=?,first=?,second=?,third=?,fourth=?,fifth=?,sixth=?,seventh=?,eighth=? where id = ?";
        return update(sql,poem.getName(),poem.getDynasty(),poem.getAuthor(),poem.getFirst(),poem.getSecond(),poem.getThird(),poem.getFourth(),poem.getFifth(),poem.getSixth(),poem.getSeventh(),poem.getEighth(),poem.getId());
    }

    @Override
    public Poem queryPoemById(Integer id) {
        String sql = "select id,name,dynasty,author,first,second,third,fourth,fifth,sixth,seventh,eighth from t_poems where id = ?";
        return  queryForOne(Poem.class,sql,id);
    }

    @Override
    public List<Poem> queryPoems() throws SQLException {

        String sql = "select id,name,dynasty,author,first,second,third,fourth,fifth,sixth,seventh,eighth from t_poems from t_poems";
        return queryForList(Poem.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_poems";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Poem> queryForPageItems(int begin, int pageSize) throws SQLException {
        String sql = "select id,name,dynasty,author,first,second,third,fourth,fifth,sixth,seventh,eighth from t_poems limit ?,?";
        return queryForList(Poem.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByAuthor(String author) {
        String sql = "select count(*) from t_poems where author = ?";
        Number count = (Number) queryForSingleValue(sql, author);
        return count.intValue();
    }

    @Override
    public List<Poem> queryForPageItemsByAuthor(int begin, int pageSize, String author) throws SQLException {
        String sql = "select id,name,dynasty,author,first,second,third,fourth,fifth,sixth,seventh,eighth from t_poems where author = ? order by id limit ?,?";
        return queryForList(Poem.class,sql,author,begin,pageSize);
    }
}
