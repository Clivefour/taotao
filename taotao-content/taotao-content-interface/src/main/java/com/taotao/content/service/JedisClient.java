package com.taotao.content.service;

public interface JedisClient {

    String set(String key, String value);
    String get(String key);
    //key是否存在
    Boolean exists(String key);
    //设置key的过期时间
    Long expire(String key, int seconds);
    //查看key的存活时间
    Long ttl(String key);
    //自增方法
    Long incr(String key);
    //存hash 散列
    Long hset(String key, String field, String value);
    //从hash散列中取数据
    String hget(String key, String field);
    //删除散列的key
    Long hdel(String key, String... field);
    //删除字符串的key
    Long del(String key);

}
