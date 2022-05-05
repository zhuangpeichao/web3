package com.dao.impl;

import com.bean.Info;
import com.bean.Type;
import com.bean.User;
import com.dao.InfoDao;
import javafx.util.Builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoDaoImpl extends BaseDao implements InfoDao {
//������
	@Override
	public int sava(Info obj) {
		return	this.update("insert into tbl_news_infor(newsTitle,newsType,newsImg,newsContent,sendUser,sendTime) values(?,?,?,?,?,?)",new Object[]
				{obj.getNewsTitle(),
				obj.getNewsType().getTypeId(),
				obj.getNewsImg(),
				obj.getNewsContent(),
				obj.getSendUser().getUserId(),
				obj.getSendTime()}		
				);
		
	}
//�������
	@Override
	public int update(Info obj) {
		return this.update("update tbl_news_infor  set newsTitle=?,newsType=?,newsContent=?,sendUser=?,sendTime=? where newsId=?", 
				
				new Object[]{obj.getNewsTitle(),obj.getNewsType().getTypeId(),obj.getNewsContent(),obj.getSendUser().getUserId(),obj.getSendTime(),obj.getNewsId()});
		
	}
//ɾ�����
	@Override
	public int delete(Integer newsid) {
		
		return this.update("delete from tbl_news_infor where newsid=?",new Object[]{newsid});
	}

	@Override
	public int deleteByType(Integer newsType) {
		return this.update("delete from tbl_news_infor where newsType=?",new Object[]{newsType});
	
	}

	
	//����id��ѯ
	@Override
	public Info select(Integer newsid) {
		ResultSet rs = null;
		
	
		try {
			rs=this.select("select * from tbl_news_infor  INNER JOIN tbl_news_type ON tbl_news_infor.newsType=tbl_news_type.typeId INNER JOIN tbl_user ON tbl_news_infor.sendUser=tbl_user.userId where newsid =?", new Object[]{newsid});
			
			while(rs.next()){
				Type type= new Type();
				Info info=new Info();
				User user= new User();
				user.setRealName(rs.getString("realName"));
				type.setTypeName(rs.getString("typeName"));
			info.setNewsId(rs.getInt("newsId"));
			info.setNewsContent(rs.getString("newsContent"));
			
			info.setNewsImg(rs.getString("newsImg"));
			info.setNewsTitle(rs.getString("newsTitle"));
			info.setNewsType(type);
			
			info.setSendTime(rs.getDate("sendTime"));
			info.setSendUser(user);
			return info;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(rs);
		}
		return null;
	}
	public int count(){
		ResultSet rs = null;
		rs=this.select("select count(*) no from tbl_news_infor ", null);
		try {
			while(rs.next()){
			int page=rs.getInt("no");
			if(page%3==0){
				return page/3;
			}else{
				int page1 =( page/3)+1;
				return page1;
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(rs);
		}
		return 0;
		
	}	

//��ҳ��ѯ
	@Override
	public List<Info> queryBypage(int pageNo, int size,String typestr,String keywords) {
		int page=(pageNo-1)*size;
		 ResultSet rs = null;
		
		try {

			StringBuilder builder=new StringBuilder("SELECT * FROM tbl_news_infor INNER JOIN tbl_news_type ON tbl_news_infor.newsType=tbl_news_type.typeId INNER JOIN tbl_user ON tbl_news_infor.sendUser=tbl_user.userId where 1=1");
			List<Object> list1=new ArrayList<Object>();
			if (typestr!=null&&!"".equals(typestr)){
				builder.append(" AND typeName = ?");
				list1.add(typestr);
			}
			if (keywords!=null&&!"".equals(keywords)){
				builder.append(" AND newsTitle like ? ");
				list1.add("%"+keywords+"%");
			}
			builder.append(" ORDER BY tbl_news_infor.newsId desc limit ?,? ");
			list1.add(page);
			list1.add(size);
			String bu = builder.toString();
			Object[] params=list1.toArray();

			rs=this.select(bu,params);
			System.out.println(bu);
			List<Info> list = new ArrayList<Info>();
			while(rs.next()){
				Type type= new Type();
				type.setTypeName(rs.getString("typeName"));
				
				User user= new User();
				user.setRealName(rs.getString("realName"));
				
			Info info=new Info();
			info.setNewsId(rs.getInt("newsId"));
			info.setNewsTitle(rs.getString("newsTitle"));
			info.setNewsType(type);
			info.setNewsImg(rs.getString("newsImg"));
			info.setNewsContent(rs.getString("newsContent"));
			info.setSendUser(user);
			info.setSendTime(rs.getDate("sendTime"));
			list.add(info);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(rs);
		}
		return null;
		
	}
	
	//�ܲ�ѯ
	@Override
	public List<Info> querAll() {
		
		ResultSet rs = null;
		
		List<Info> list=new ArrayList<Info>();
		try {
			rs=this.select("SELECT * FROM tbl_news_infor INNER JOIN tbl_news_type ON tbl_news_infor.newsType=tbl_news_type.typeId INNER JOIN tbl_user ON tbl_news_infor.sendUser=tbl_user.userId", null);
			while(rs.next()){
				Type type = new Type();
				type.setTypeName(rs.getString("typeName"));
				User user= new User();
				user.setRealName(rs.getString("realName"));
				
				
			Info info=new Info();
			info.setNewsId(rs.getInt("newsId"));
			info.setNewsTitle(rs.getString("newsTitle"));
			info.setNewsType(type);
			info.setNewsImg(rs.getString("newsImg"));
			info.setNewsContent(rs.getString("newsContent"));
			info.setSendUser(user);
			info.setSendTime(rs.getDate("sendTime"));
			list.add(info);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(rs);
		}
		return null;
		
		
		
		
	}

}
