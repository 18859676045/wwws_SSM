package com.www.controller;


import com.www.main.Role;
import com.www.main.UserInfo;
import com.www.services.IRoleService;
import com.www.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    public IUserService userService;


    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] ids){

        userService.saveRoleToUser(userId,ids);

        return "redirect:findAll.do";
    }


    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String id) throws Exception {
        UserInfo user=userService.findById(id);
        List<Role> roleList=userService.findByOtherRole(id);
        System.out.println("userService------"+userService);
        System.out.println("roleList++++++++"+roleList);
        ModelAndView mv=new ModelAndView("user-role-add");
        mv.addObject("roleList",roleList);
        mv.addObject("user",user);
        return mv;
    }



    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
       List<UserInfo> userInfos= userService.findAll();
        mv.addObject("userList",userInfos);
        mv.setViewName("user-list");

        return mv;
    }


    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception
    {
        userService.saveOne(userInfo);

        return "redirect:findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id")String id) throws Exception{
       UserInfo   list= userService.findById(id);
       ModelAndView mv=new ModelAndView("user-show");
       mv.addObject("user",list);

       return mv;
    }


}
