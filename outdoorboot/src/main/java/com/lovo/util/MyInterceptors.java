package com.lovo.util;

import com.lovo.config.StringUtil;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptors implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           //判断用户是否登录
            //1、获取本地session
        HttpSession session= request.getSession();
      String password= (String) session.getAttribute("password");
      String tag= (String) session.getAttribute("page_tag");
      if(null==password||"".equals(password)) {
          //2、如果本地session不存在就去获取远程redis中的用户
          Jedis jedis=new Jedis("127.0.0.1",6379);
         String r_password= jedis.get(tag);
         if(StringUtil.blString(r_password)){
           //在redeis中存在，就把密码放入到本地session
             session.setAttribute("password",r_password);
             return  true;
         }else {
             //3、如果redis中也没用就返回登录页面
          request.setAttribute("goLogin",true);
         }
      }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    //
      Object object=  request.getAttribute("goLogin");
      if(null!=object) {
          boolean bl= (boolean)object;
          if (bl) {
              modelAndView.setViewName("login");
          }
      }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
