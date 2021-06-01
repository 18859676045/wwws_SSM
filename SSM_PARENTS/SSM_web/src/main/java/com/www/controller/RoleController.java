package com.www.controller;


import com.www.main.Permission;
import com.www.main.Role;
import com.www.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] ids){
        for (String id : ids) {
            roleService.savePermissionToRole(id, roleId);
        }
        return "redirect:findAll.do";
    }




    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String id) throws Exception {

       Role role= roleService.findRole(id);
       ModelAndView mv=new ModelAndView("role-permission-add");
       List<Permission> permissionList= roleService.findByOtherPermission(id);
       mv.addObject("role",role);
       mv.addObject("permissionList",permissionList);
       return mv;

    }






    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView("role-list");
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList",roles);
        return mv;
    }


    @RequestMapping("/save.do")
    public String save( Role role) throws Exception {
         roleService.save(role);

        return "redirect:findAll.do";
    }


}
