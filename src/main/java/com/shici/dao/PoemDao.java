package com.shici.dao;

import com.shici.pojo.Poem;

import java.sql.SQLException;
import java.util.List;

public interface PoemDao {
    public int addPoem(Poem poem);

    public int deletePoemById(Integer id);

    public int updatePoem(Poem poem);

    public Poem queryPoemById(Integer id);

    public List<Poem> queryPoems() throws SQLException;

    Integer queryForPageTotalCount();

    List<Poem> queryForPageItems(int begin, int pageSize) throws SQLException;

    Integer queryForPageTotalCountByAuthor(String author);

    List<Poem> queryForPageItemsByAuthor(int begin, int pageSize, String author) throws SQLException;
}
