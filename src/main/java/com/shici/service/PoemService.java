package com.shici.service;

import com.shici.pojo.Page;
import com.shici.pojo.Poem;

import java.sql.SQLException;
import java.util.List;

public interface PoemService {

    public void addPoem(Poem poem);
    public void deletePoemById(Integer id);
    public void updatePoem(Poem poem);
    public Poem queryPoemById(Integer id);
    public List<Poem> queryPoems() throws SQLException;

    Page<Poem> page(int pageNo, int pageSize) throws SQLException;
    Page<Poem> pageByAuthor(int pageNo, int pageSize, String author) throws SQLException;
}
