package com.www.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.www.main.Orders;
import com.www.services.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService iOrdersService;




    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer
    page,@RequestParam(name = "size",required = true,defaultValue = "5") Integer size) throws Exception
    {
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList = iOrdersService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        Orders orders=iOrdersService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("orders-show");
        mv.addObject("orders",orders);
        return mv;
    }

}
