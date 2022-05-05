package com.dao.impl;

import com.bean.Type;
import com.dao.TypeDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDaoImpl extends BaseDao implements TypeDao{

	@Override
	public List<Type> queryAll() {
		ResultSet rs = null;
		rs=this.select("select * from tbl_news_type", null);
		List<Type> list=new ArrayList<Type>();
		try {
			while(rs.next()){
			Type type=new Type();
			type.setTypeId(rs.getInt("typeId"));
			type.setTypeName(rs.getString("typeName"));
			list.add(type);
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
