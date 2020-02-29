package com.lovo.service;

import com.alibaba.fastjson.JSONArray;
import com.lovo.dao.UserDao;
import com.lovo.dao.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements IUserService {
     //注入DAO
    @Autowired
  private   UserDao userDao;

    @Autowired
   private Jedis sprinJedis;

    public List<UserEntity> getListUser() {
        return (List<UserEntity>) userDao.findAll();
    }

    @Override
    public List<UserEntity> getUserListByAgeOrName(int age, String userName) {
        List<UserEntity> list=null;
        //1、从缓存中查询
            String key=age+"_"+userName;
          String resultStr=  this.getRedis(key);
        //2、如果缓存中没有数据，就去mysql中查询
        if(StringUtils.isEmpty(resultStr)){
            //结果为空，就去数据库里面查询
        list=  userDao.getUserListByAgeOrName(age,userName);
        //放入到缓存
           //1、把集合转换为json
            if(null!=list&&list.size()>0) {
                //在数据库里面查询到了结果
             String valJson=   JSONArray.toJSONString(list);
                this.setRedis(key,valJson);
            }else {
                //在数据库里面没有查询到结果
                this.setRedis(key,"");
            }
        }else{
            //在缓存中查询出的结果是json字符串
            //把json字符串转换为对象
            list= JSONArray.parseArray(resultStr,UserEntity.class);
        }
        //3、把结果放入到缓存
        return list;
    }

    /**
     * 从redis中查询
     * @param key
     * @return
     */
    public String getRedis(String key){
       //redis 能放 byte[] -序列化和反序列化 和 string -->对象-->json,json-object
      String resultStr=  sprinJedis.get(key);
        sprinJedis.close();
        return resultStr;
    }

    public  void setRedis(String key,String val){
          sprinJedis.set(key,val);
          sprinJedis.setex(key,1000*60*5,val);
          sprinJedis.close();
    }

}
