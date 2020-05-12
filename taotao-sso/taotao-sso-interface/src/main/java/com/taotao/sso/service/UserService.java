package com.taotao.sso.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
    /**
     * 校验数据的方法
     * @param param 需要校验的数据
     * @param type 类型 1 用户名 2 手机号码 3 邮箱地址
     * @return true代表数据可用 ， false代表数据不可用
     */
    TaotaoResult getCheckDateResult(String param, Integer type);

    /**
     * 用户注册方法
     * @param tbUser 用户信息 ，注意需要校验信息
     * @return 200表示成功 500表示失败
     */
    TaotaoResult addUser(TbUser tbUser);

    /**
     * 登录方法
     * @param userName 用户账号
     * @param passWord 用户密码 注意要加密验证
     * @return token是一个随机字符串
     */
    TaotaoResult getTokenByNameAndPass(String userName, String passWord);

    /**
     * 根据token查询用户信息
     * @param token token
     * @return 用户信息的json字符串
     */
    TaotaoResult getUserByToken(String token);
}
