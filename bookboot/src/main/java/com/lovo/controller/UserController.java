package com.lovo.controller;

import com.lovo.dao.UserEntity;
import com.lovo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    IUserService userService;
    @RequestMapping("getUserListByAgeOrName")
    public List<UserEntity> getUserList(int age,String userName){

      return   userService.getUserListByAgeOrName(age,userName);
    }
}
