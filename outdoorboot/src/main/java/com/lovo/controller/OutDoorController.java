package com.lovo.controller;

import com.lovo.vo.GuidVo;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class OutDoorController {
    @RequestMapping("login")
    public  ModelAndView login(String password,HttpServletRequest request){
      ModelAndView mv=new ModelAndView();
      String passwordDB="lovo";//模拟从数据库中查询出来的密码
        boolean bl=false;
        HttpSession httpSession=request.getSession();

       if(password.equals(passwordDB)){
         //登录成功
           //1、生成TAG,2、把tag放入到redies(key(tag)-val(password))
           bl=true;
       }
    if(bl) { //登录成功
     String  tag= (String) httpSession.getAttribute("page_tag");

    }
      return mv;
    }

    @RequestMapping("outdoorList")
    public ModelAndView outdoorList(){
        ModelAndView mv=new ModelAndView("outdoorList");
        List<String> list= new ArrayList<>();
        list.add("户外用品1");
        list.add("户外用品2");
        list.add("户外用品3");
        mv.addObject("list",list);
        return  mv;
    }
}
