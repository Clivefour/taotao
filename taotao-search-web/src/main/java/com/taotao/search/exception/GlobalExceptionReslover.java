package com.taotao.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class GlobalExceptionReslover implements HandlerExceptionResolver{
    Logger logger = LoggerFactory.getLogger(GlobalExceptionReslover.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("系统发生异常",e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "系统发生异常，请稍后重试");
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }
}
