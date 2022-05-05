package com.service.impl;

import com.bean.Type;
import com.dao.TypeDao;
import com.dao.impl.TypeDaoImpl;
import com.service.ServiceTypeDao;

import java.util.List;

public class ServiceTypeImpl implements ServiceTypeDao {
    TypeDao typeDao = new TypeDaoImpl();

    @Override
    public List<Type> queryAll() {
        return this.typeDao.queryAll();
    }
}
