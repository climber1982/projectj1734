package com.lovo.redies;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class T {

    public static void main(String[] args) {
        //1、启动1个数据库
        Jedis  jedis1=new Jedis("127.0.0.1",8000);
      //  Jedis  jedis2=new Jedis("127.0.0.1",8001);
        //2、向数据库中放入数据
        Map<String,String> zy=new HashMap<>();
        zy.put("userName","赵云");
        zy.put("age","30");
        jedis1.hmset("zy",zy);

        Map<String,String> zf=new HashMap<>();
        zf.put("userName","张飞");
        zf.put("age","40");
        jedis1.hmset("zf",zf);
           jedis1.close();
        //3、根据条件查询数据
        System.out.println(T.getUserName("zf"));

    }

    public  static String getUserName(String  key ){
        //1、启动2个数据库
        Jedis  jedis1=new Jedis("127.0.0.1",8000);
        Jedis  jedis2=new Jedis("127.0.0.1",8001);
         if(key.equals("zy")){
             //如果为zy就去第一个数据库里面取
             System.out.println("进入第一个数据库");
          String str=   jedis1.hget("zy","userName");
             jedis1.close();
          return   str;

         }else  if(key.equals("zf")){
             //如果为zf就去第二个数据库里面取
             System.out.println("进入第二个数据库");
             String str=   jedis2.hget("zf","userName");
             jedis2.close();
             return   str;
         }

        return  "";
    }

}
