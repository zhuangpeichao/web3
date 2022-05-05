package com.service.impl;

import com.bean.Info;
import com.dao.InfoDao;
import com.dao.impl.InfoDaoImpl;
import com.service.ServiceInfoDao;

import java.util.List;

public class ServiceInfoImpl implements ServiceInfoDao {
    InfoDao infoDao = new InfoDaoImpl();
    @Override
    public int sava(Info obj) {
        return this.infoDao.sava(obj);
    }

    @Override
    public int update(Info obj) {
        return this.infoDao.update(obj);
    }

    @Override
    public int delete(Integer newsid) {
        return this.infoDao.delete(newsid);
    }

    @Override
    public int deleteByType(Integer newsType) {
        return this.infoDao.deleteByType(newsType);
    }

    @Override
    public Info select(Integer newsid) {
        return this.infoDao.select(newsid);
    }

    @Override
    public List<Info> queryBypage(int pageNo, int size,String  typestr,String keywords) {
        return this.infoDao.queryBypage(pageNo,size,typestr,keywords);
    }

    @Override
    public List<Info> querAll() {
        return this.infoDao.querAll();
    }
}
