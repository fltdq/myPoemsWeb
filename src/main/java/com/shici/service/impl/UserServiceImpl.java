package com.shici.service.impl;

import com.shici.dao.BaseDao;
import com.shici.dao.UserDao;
import com.shici.dao.impl.UserDaoImpl;
import com.shici.pojo.User;
import com.shici.service.UserService;

/**
 * FileName: UserServiceImpl
 * Description:
 * Author: CSH
 * Date: 2020/12/23 16:23
 * Version: 1.0
 */
public class UserServiceImpl extends BaseDao implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null){
            //等于null，说明没查到，没查到表示可用
            return false;
        }
        return true;
    }
}
