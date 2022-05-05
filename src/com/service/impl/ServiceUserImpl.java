package com.service.impl;

import com.bean.User;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.ServiceUserDao;

public class ServiceUserImpl implements ServiceUserDao {

    UserDao userDao=new UserDaoImpl();
    @Override
    public User queryByName(String userName) {
        return this.userDao.queryByName(userName);
    }

    @Override
    public User queryByNames(String userName, String userPwd) {
        return this.userDao.queryByNames(userName,userPwd);
    }
}
