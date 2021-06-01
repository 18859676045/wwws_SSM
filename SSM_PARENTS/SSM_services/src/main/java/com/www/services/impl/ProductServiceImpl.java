package com.www.services.impl;

import com.www.dao.IProductDao;
import com.www.main.Product;
import com.www.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * ���ҳ־ò�
 */
@Service
@Transactional
public class ProductServiceImpl implements IProductService{


    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> findAll() throws Exception {
        return iProductDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        iProductDao.save(product);
    }

    @Override
    public void deleteOne(String  id) {

                iProductDao.deleteOne(id);
    }
}
