package com.www.services;

import com.www.main.SysLog;

import java.util.List;

public interface ISysLogService {

    public void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(int page,int size);
}
