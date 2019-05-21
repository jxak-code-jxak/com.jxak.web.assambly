package com.jxak.education.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/main/index",method = RequestMethod.GET)
    public String test() {
        return "/index";
    }

    /**
     * 安全教育培训总览
     * @return
     */
    @RequestMapping(value = "/main/pxzl",method = RequestMethod.GET)
    public String opPxZl() {
        return "/pxzl";
    }

    /**
     * 安全教育培训制度
     * @return
     */
    @RequestMapping(value = "/main/opZhiDu",method = RequestMethod.GET)
    public String opZhiDu(){
        return "/zhidu";
    }

    /**
     *安全教育培训需求
     * @return
     */
    @RequestMapping(value = "/main/opXuQiu",method = RequestMethod.GET)
    public String opXuQiu(){
        return "/xuqiu";
    }

    /**
     * 安全教育培训需求 编辑
     * @return
     */
    @RequestMapping(value = "/xuqiu/xuqiuEdit",method = RequestMethod.GET)
    public String opXuqiuEdit(){
        return "/xuqiuEdit";
    }

    /**
     * 安全教育培训需求 编辑
     * @return
     */
    @RequestMapping(value = "/userEdit",method = RequestMethod.GET)
    public String userEdit(){
        return "/userEdit";
    }
    /**
     *安全教育培训计划
     * @return
     */
    @RequestMapping(value = "/main/opJiHua",method = RequestMethod.GET)
    public String opJiHua(){
        return "/jihua";
    }

    /**
     *安全教育培训计划 编辑
     * @return
     */
    @RequestMapping(value = "/main/opJiHuaEdit",method = RequestMethod.GET)
    public String opJiHuaEdit(){
        return "/jhEdit";
    }

    /**
     *培训教材
     * @return
     */
    @RequestMapping(value = "/emerg",method = RequestMethod.GET)
    public String emergConfig(){
        return "/emerg";
    }

    /**
     *数据展示
     */
    @RequestMapping(value = "/datas",method = RequestMethod.GET)
    public String emergData(Model model, @RequestParam("terId") String terId){
        model.addAttribute("terId", terId);
        return "/datas";
    }

    /**
     * 费用台账
     * @return
     */
    @RequestMapping(value = "/main/opFyTz",method = RequestMethod.GET)
    public String opFyTz(){
        return "/fytz";
    }

    /**
     * 费用台账编辑
     * @return
     */
    @RequestMapping(value = "/main/opFyTzEdit",method = RequestMethod.GET)
    public String opFyTzEdit(){
        return "/fytzEdit";
    }
    /**
     * 极卷网络
     * @return
     */
    @RequestMapping(value = "/main/opJjWl",method = RequestMethod.GET)
    public String opJjWl(){
        return "/jjwl";
    }
    /**
     * 自定义404
     * @return
     */
    @RequestMapping(value = "/error/404",method = RequestMethod.GET)
    public String to404(){
        return "/error/404";
    }

    /**
     * 自定义500
     * @return
     */
    @RequestMapping(value = "/error/500",method = RequestMethod.GET)
    public String to500(){
        return "/error/500";
    }
    
    @RequestMapping(value = "/main/println",method = RequestMethod.GET)
    public String println() {
    	
    	return "/println";
    }
}
