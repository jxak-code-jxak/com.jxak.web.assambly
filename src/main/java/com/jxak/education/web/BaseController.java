package com.jxak.education.web;

import com.jxak.education.utils.MyException;
import com.jxak.education.utils.ResponseT;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.UnexpectedTypeException;
import java.sql.SQLException;

/** @Author liaoyuanjie
 * @Description //TODO 统一异常处理
 * @Date 10:03 2019/5/16
 * @Param
 * @return
 **/
@RestControllerAdvice
public class BaseController {

    @ExceptionHandler(value = Exception.class)
    public Object exceptionGet(Exception e, HttpServletRequest request, HttpServletResponse response) {

        //返回前端的数据格式
        ResponseT<?> result = null;
        //是否为自定义异常
        if (e instanceof MyException) {

        }
        //其它再拦截器以及其它无法直接抛出异常的地方抛出的异常只能自己判断，类似与shiro的权限不足异常
        else if (e instanceof UnexpectedTypeException){

        }
        //sql相关错误
        else if (e instanceof SQLException){

        }
        //不属于以上所有异常，按未定义异常处理
        else {

        }
        //判断是否为ajax请求，是则返回json格式数据，不是则返回ModelAndView对象
        if(isAjax(request)) {
            return result;
        }
        else {
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("error",result.getMsg());
            return mav;
        }
    }
    //判断是否为ajax请求
    boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}
