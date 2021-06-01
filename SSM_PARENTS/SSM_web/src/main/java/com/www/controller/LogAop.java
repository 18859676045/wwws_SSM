package com.www.controller;


import com.www.main.SysLog;
import com.www.services.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;//访问的IP

    @Autowired
    private ISysLogService sysLogService;

    private Date startTime;            //访问开始时间
    private Class executionClass;      //访问的类
    private Method executionMethod;    //访问的方法


    //前置通知
    //主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.www.controller.*.*(..))")
    public void doBefore(JoinPoint jp)throws Exception{
        //开始访问的时间
        startTime=new Date();

        //具体要访问的类
        executionClass = jp.getTarget().getClass();

        //获取访问的方法的名称
        String MetherName =  jp.getStaticPart().getSignature().getName();
        //获取访问的方法的参数
        Object[] args = jp.getArgs();


        //获取具体执行的方法的Method对象
        if (args==null||args.length==0){ //无参数
            executionMethod=executionClass.getMethod(MetherName);//只能获取无参数方法
        }else {
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs=new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i]=args[i].getClass();
            }
            //获取有参数的方法名
            executionMethod=executionClass.getMethod(MetherName,classArgs);
        }
    }

    //后置通知
    //主要获取日志中其它信息，时长、ip、url
    @After("execution(* com.www.controller.*.*(..))")
    public void doAfter(JoinPoint jp)throws Exception {

        if (executionClass!=SysLogController.class){

            // 获取类上的@RequestMapping对象
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                // 获取方法上的@RequestMapping对象
                RequestMapping methodannotation = executionMethod.getAnnotation(RequestMapping.class);
                //方法不为空
                if (methodannotation!=null){
                    String url="";// 它的值应该是类上的@RequestMapping的value+方法上的@RequestMapping的value
                    //获取访问路径
                    url=classAnnotation.value()[0]+methodannotation.value()[0];
                    //新建一个日志类


                    SysLog sysLog=new SysLog();
                    // 获取访问时长
                    Long excutionTime=new Date().getTime()-startTime.getTime();
                    //添加访问时长
                    sysLog.setExecutionTime(excutionTime);


                    //添加路径
                    sysLog.setUrl(url);


                    //获取IP
                    //已经在web.xml配置spring的request监听器
                    String ip = request.getRemoteAddr();
                    //设置IP
                    sysLog.setIp(ip);


                    // 可以通过securityContext获取，也可以从request.getSession中获取
                    SecurityContext context = SecurityContextHolder.getContext();
                    String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();
                    //添加当前访问的用户
                    sysLog.setUsername(username);


                    //添加访问方法
                    sysLog.setMethod("[类名]"+executionClass.getName()+ "[方法名]"+executionMethod.getName());

                    //添加访问时间
                    sysLog.setVisitTime(startTime);


                    // 调用Service，调用dao将sysLog insert数据库
                    sysLogService.save(sysLog);

                }
            }
        }
    }
}

























