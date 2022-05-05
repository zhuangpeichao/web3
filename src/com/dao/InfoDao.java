package com.dao;

import com.bean.Info;

import java.util.List;

public interface InfoDao {
	public int sava(Info obj);//添加
	public int update(Info obj);//更新
	public int delete(Integer newsid);//删除
	public int deleteByType(Integer newsType);//根据类型删除
	
	public Info select(Integer newsid);//根据id查询
	public List<Info>queryBypage(int pageNo, int size,String typestr,String keywords);//分页查询
	public List<Info> querAll();//查询所有
	
}
