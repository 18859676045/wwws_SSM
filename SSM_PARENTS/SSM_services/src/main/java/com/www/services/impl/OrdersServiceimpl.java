package com.www.services.impl;

import com.github.pagehelper.PageHelper;
import com.www.dao.IOrdersDao;
import com.www.main.Orders;
import com.www.services.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceimpl implements IOrdersService{

    @Autowired
    private IOrdersDao iOrdersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception{
        PageHelper.startPage(page,size);

        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return iOrdersDao.findById(id);
    }
}
