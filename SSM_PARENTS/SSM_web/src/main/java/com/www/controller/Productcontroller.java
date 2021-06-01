package com.www.controller;


import com.www.main.Product;
import com.www.main.UserInfo;
import com.www.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Proxy;
import java.util.List;

@Controller
@RequestMapping("/product")
public class Productcontroller {

    @Autowired
    public IProductService iProductService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Product> serviceAll = iProductService.findAll();
        mv.addObject("productList",serviceAll);
        mv.setViewName("product-list");

        return mv;
    }

    @RequestMapping("/save.do")
    public String saveOne(Product product) throws Exception {
        iProductService.save(product);
        return "redirect:findAll.do";

    }


    @RequestMapping(value = "/delete.do")
    public String deleteOne(@RequestBody List<String> selectList) throws Exception{
        System.out.println("开始执行删除");
        for (String id : selectList) {
            System.out.println(id);
            iProductService.deleteOne(id);
        }
        return "redirect:findAll.do";
    }




    @RequestMapping("/find.do")
    public String findAll(Model model) throws Exception {
        List<Product> all = iProductService.findAll();


        model.addAttribute("list",all);
        return "success";
    }




}
