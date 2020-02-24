package com.lovo.controller;

import com.lovo.vo.GuidVo;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class GuideController {

    @RequestMapping("gotoIndex")
    public ModelAndView gotoIndex(HttpServletRequest request){
        HttpSession session =request.getSession();
        //生成一个UUID放入到session
        String uuid= UUID.randomUUID().toString();
        session.setAttribute("page_tag",uuid);
        ModelAndView mv=new ModelAndView();
       //创建导航集合
        List<GuidVo> list=new ArrayList<>();
        GuidVo vo1=new GuidVo();
        vo1.setUrl("outdoorList?tag="+uuid);
        vo1.setUrlName("户外");
        GuidVo vo2=new GuidVo();
        vo2.setUrl("bookList?tag="+uuid);
        vo2.setUrlName("书籍");
       list.add(vo1);
       list.add(vo2);
        mv.addObject("list",list);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("gotoLogin")
    public  String gotoLogin(){
        return "loin";
    }
}
