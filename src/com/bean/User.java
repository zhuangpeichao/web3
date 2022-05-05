package com.bean;

import java.io.Serializable;

/**
 * �û���
 * ���Ժ�����һһ��Ӧ
 * һ�����Ӧһ����
 * 一一对应
 * 实现序列化id
 * @author 26986
 *1
 */
public class User implements Serializable{

	/**
	 * 实现序列化id
	 */
	private static final long serialVersionUID = -1205251036584870584L;
	/**
	 * �û�id
	 */
	private  Integer userId;
	/**
	 * �û�����
	 */
	private String realName;
	/**
	 * �û��˺�
	 */
	private String userName;
	/**
	 * �û�����
	 */
	private String userPwd;
	
	public User() {
		super();
	}
	
	public User(Integer userId, String realName, String userName, String userPwd) {
		super();
		this.userId = userId;
		this.realName = realName;
		this.userName = userName;
		this.userPwd = userPwd;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
