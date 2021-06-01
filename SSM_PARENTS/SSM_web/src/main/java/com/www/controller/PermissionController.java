package com.www.controller;


import com.www.main.Permission;
import com.www.services.IPermissionService;
import com.www.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController{


    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView("permission-list");
        List<Permission> permissions = permissionService.findAll();

        mv.addObject("permissionList",permissions);

        return mv;

    }


    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);

        return "redirect:findAll.do";
    }


}
