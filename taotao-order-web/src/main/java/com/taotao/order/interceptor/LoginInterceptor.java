package com.taotao.order.interceptor;

import com.taotao.constant.RedisConstant;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import com.taotao.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{
    @Autowired
    private UserService userService;
    /**
     * 进入controller之前
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /**
         * 用户没有登录就不放行
         *
         */
        //获取当前url路径地址
        String url = httpServletRequest.getRequestURL().toString();
        String token = CookieUtils.getCookieValue(httpServletRequest, RedisConstant.TT_TOKEN);
        if (StringUtils.isBlank(token)) {
            //跳转登录页面 并且把当前url地址 作为参数 传递过去
            httpServletResponse.sendRedirect(RedisConstant.SSO_LOGIN_URL+"?redirectUrl="+url);
            return false;
        }
        //走到这里 可以从cookie中获取token 但是由于cookie 和redis的过期时间可能不一样 所以必须再次 从redis中获取用户信息
        TaotaoResult result = userService.getUserByToken(token);
        if(result.getStatus()!=200){
            httpServletResponse.sendRedirect(RedisConstant.SSO_LOGIN_URL+"?redirectUrl="+url);
            return false;
        }
        //走到这里 表示用户一定已经登录了
        TbUser tbUser = (TbUser) result.getData();
        //
        httpServletRequest.setAttribute("user",tbUser);

        return true;
    }

    /**
     * 进入controller之中 但是没有返回 modelandView之前
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 进入controller之后 返回modelandview之后
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
