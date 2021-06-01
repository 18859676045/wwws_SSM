package com.www.services.impl;

import com.github.pagehelper.PageHelper;
import com.www.dao.ISysLogDao;
import com.www.main.SysLog;
import com.www.services.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceimpl implements ISysLogService{

    @Autowired
    private ISysLogDao sysLogDao;


    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);

    }

    @Override
    public List<SysLog> findAll(int page,int size) {
        PageHelper.startPage(page,size);

        return sysLogDao.findAll();
    }
}
