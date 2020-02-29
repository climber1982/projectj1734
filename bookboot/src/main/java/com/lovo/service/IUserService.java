package com.lovo.service;

import com.lovo.dao.UserEntity;

import java.util.List;

public interface IUserService {
    public String getRedis(String key);
    public List<UserEntity> getListUser();

    public List<UserEntity> getUserListByAgeOrName(int age, String userName);
}
