package com.jxak.education.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
    @RequestMapping(value = "/main/index",method = RequestMethod.GET)
    public String test() {
        return "/index";
    }
    @RequestMapping(value = "/main/opZhiDu",method = RequestMethod.GET)
    public String opZhiDu(){
        return "/zhidu";
    }
    @RequestMapping(value = "/main/opXuQiu",method = RequestMethod.GET)
    public String opXuQiu(){
        return "/xuqiu";
    }
    @RequestMapping(value = "/main/opJiHua",method = RequestMethod.GET)
    public String opJiHua(){
        return "/jihua";
    }
    @RequestMapping(value = "/xuqiu/xuqiuEdit",method = RequestMethod.GET)
    public String opXuqiuEdit(){
        return "/xuqiuEdit";
    }
    @RequestMapping(value = "/userEdit",method = RequestMethod.GET)
    public String userEdit(){
        return "/userEdit";
    }
    @RequestMapping(value = "/error/404",method = RequestMethod.GET)
    public String to404(){
        return "/error/404";
    }
    @RequestMapping(value = "/error/500",method = RequestMethod.GET)
    public String to500(){
        return "/error/500";
    }
}
