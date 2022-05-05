package com.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	//��ȡ�����ļ�
static{
	Properties props = new Properties();
	InputStream is=BaseDao.class.getClassLoader().getResourceAsStream("peizhi.properties");
	try {
		props.load(is);
		driver=props.getProperty("driver");
		url=props.getProperty("url");
		user=props.getProperty("user");
		password=props.getProperty("password");
		
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		Class.forName(driver);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
}
//ͨ����ɾ��
	public int update(String sql,Object[]zhi){
		Connection	coon=null;
		PreparedStatement ps=null;
	try {
		coon=DriverManager.getConnection(url, password,user);
	ps=coon.prepareStatement(sql);
	if(zhi!=null){
		for (int i = 0; i < zhi.length; i++) {	
			ps.setObject(i+1, zhi[i]); 
		}
	}
   int b=ps.executeUpdate();  
	this.closeAll(coon, ps, null);
	return b;
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;
	}
	
	//ͨ�ò�ѯ
	public ResultSet select(String sql,Object[]zhi){
		Connection	coon=null;
		PreparedStatement ps=null;
	try {
		coon=DriverManager.getConnection(url, password,user);
		/*coon=DriverManager.getConnection("jdbc:mysql://localhost:3306/sjk", "root","root");*/
		
		ps=coon.prepareStatement(sql);
	if(zhi!=null && zhi.length>0){
		for (int i = 0; i < zhi.length; i++) {	
			ps.setObject(i+1, zhi[i]); 
		}
	}
	return ps.executeQuery();
	
	 
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
	
	
	
	//�ͷ���Դ
	public void closeAll(Connection d,Statement e,ResultSet r) throws SQLException{
		if(r!=null){
			r.close();
		}
		if(e!=null){
			e.close();
		}
		if(d!=null){
			d.close();
		}
	}
	
	public void close(ResultSet r){
		Connection conn=null;
		Statement stmt=null;
		try {
			if(r==null){
				return;
			}else{
			stmt = r.getStatement();
			conn = stmt.getConnection();
			this.closeAll(conn,stmt,r);	
			}	
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
