package com.www.controller;


import com.github.pagehelper.PageInfo;
import com.www.main.SysLog;
import com.www.services.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "5")Integer size)
    {
        ModelAndView mv=new ModelAndView("syslog-list");
       List<SysLog> sysLogList= sysLogService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(sysLogList);

        mv.addObject("pageInfo",pageInfo);

       return mv;

    }
}
