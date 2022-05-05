package com.dao;

import com.bean.User;

public interface UserDao {
	public User queryByName(String userName);//根据账号查询
	public User queryByNames(String userName,String userPwd);//根据姓名查询
}
