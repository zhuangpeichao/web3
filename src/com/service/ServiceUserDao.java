package com.service;

import com.bean.User;

public interface ServiceUserDao {
    public User queryByName(String userName);//根据账号查询
    public User queryByNames(String userName,String userPwd);//根据姓名查询
}
