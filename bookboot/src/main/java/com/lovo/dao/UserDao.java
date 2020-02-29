package com.lovo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<UserEntity,Integer> {
     @Query("from UserEntity where age=?1 or userName=?2")
    public List<UserEntity> getUserListByAgeOrName(int age, String userName);

}
