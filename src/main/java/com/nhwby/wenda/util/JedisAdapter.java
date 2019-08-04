package com.nhwby.wenda.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JedisAdapter implements InitializingBean {
    private JedisPool pool ;
    public static void print(int index, Object object) {
        System.out.println(String.format("%d,%s", index, object.toString()));

    }

    public static void main(String[] args) {
        Jedis  jedis =new Jedis("redis://localhost:6379/9");
     jedis .flushDB();//删除数据库
        jedis .set("hello","world");
        print(1,jedis .get("hello")) ;
        jedis .rename("hello", "newhello");
//15秒有效时期  比如用于验证码发送过后就失效了
     jedis.setex("hello2",15,"world");
     jedis.set("pv","100");
     jedis .incr("pv");//pv初始值为100，自动增加1 用于浏览人数
 print(3,jedis .keys ("*")); //打印所有的key,3是打印的索引吧


    }

    @Override
    public void afterPropertiesSet() throws Exception {
      pool =new JedisPool("redis://localhost:6379/10");
    }
    public  void  sadd
}
