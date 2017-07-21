package com.taotao.rest.dao;

/**
 * Created by geek on 2017/6/30.
 */
public interface JedisClient {

    String get(String key);
    String set(String key,String value);
    String hget(String hkey,String key);
    long hset(String hkey, String key, String value);
    long incr(String key);
    long expire(String key, int second);
    long ttl(String key);
    long hdel(String hkey,String key);

}
