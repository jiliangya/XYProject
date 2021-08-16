package com.chong.xinyuproject_new.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController //对类中的所有处理器返回的结果都为字符创类型
//@Controller //这个类就是一个处理器类
public class OneController {

//    @RequestMapping("/myHello") //访问的路径
//    @ResponseBody //可以返回字符串值
//    public String hello(){
//        return "hello world!!!";
//    }

//    @RequestMapping("/test01")
//    @ResponseBody
//    public String test01(){
//        return "好呀好呀";
//    }

    /**
     * @RequestMapping 这个注解是可以接收所有的请求方式
     * @return
     */
//    @RequestMapping(value="/hello",method = RequestMethod.POST)
    @GetMapping("/hello")
    public String hello(){
        return "hello world!!!";
    }




}
