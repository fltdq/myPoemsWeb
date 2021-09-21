package com.shici.dao.impl;

import com.shici.dao.BaseDao;
import com.shici.dao.UserDao;
import com.shici.pojo.User;

/**
 * FileName: UserDaoImpl
 * Description:
 * Author: CSH
 * Date: 2020/12/23 18:43
 * Version: 1.0
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "Select id,username,password,email from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
