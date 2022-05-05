package com.dao.impl;

import com.bean.User;
import com.dao.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User queryByName(String userName) {
		ResultSet rs= null;
		
		try {
			rs=	this.select("selsect * from tbl_user where userName = ?",new  Object[]{userName});
			while(rs.next()){
				User user= new User();
				user.setUserName(rs.getString("userName"));
				user.setRealName(rs.getString("realName"));
				user.setUserId(rs.getInt("userId"));
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(rs);
		}
		
			return null;
		}


	@Override
	public User queryByNames(String userName, String userPwd) {
		ResultSet rs= null;
		
	try {
		rs=	this.select("select * from tbl_user where userName =? and userPwd=?",new  Object[]{userName,userPwd});
		while(rs.next()){
			User user= new User();
			user.setUserName(rs.getString("userName"));
			user.setUserPwd(rs.getString("userPwd"));
			user.setUserId(rs.getInt("userId"));
			return user;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		this.close(rs);
	}
	
		return null;
	}

}
