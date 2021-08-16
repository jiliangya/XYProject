package com.chong.xinyuproject_new.controller;


import com.chong.xinyuproject_new.pojo.User;
import com.chong.xinyuproject_new.service.UserService;
import com.chong.xinyuproject_new.utils.CommonResponse;
import com.chong.xinyuproject_new.utils.LoginResponse;
import com.chong.xinyuproject_new.utils.SnowFlake;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private RedisTemplate redisTemplate;


    @PostMapping("/login")
    public CommonResponse login(@Validated @RequestBody User user){
        String message="登陆失败,用户名或密码错误";
        boolean success = false;
        User loginUser=  userService.login(user);
        LoginResponse loginResponse =null;
        if(loginUser!=null){
            message="登陆成功";
            success=true;
            //得到字符串token
            String token = String.valueOf(snowFlake.nextId());
            //把token和登陆成功的用户存储到redis中
            redisTemplate.opsForValue().set(token,loginUser,3600*24, TimeUnit.SECONDS);

            //把登陆成功的数据，封装到登陆响应对象中
            loginResponse = new LoginResponse();
            loginResponse.setId(loginUser.getId());
            loginResponse.setLoginName(loginUser.getLoginName());
            loginResponse.setName(loginUser.getName());
            loginResponse.setToken(token);



        }
        CommonResponse res = new CommonResponse(success,message,loginResponse);
        return res;
    }


    @GetMapping("/loginOut")
    public CommonResponse loginOut(String token){
        String message ="未接受到token";
        boolean success =false;
        if(!ObjectUtils.isEmpty(token)){
            redisTemplate.delete(token);//清除redis中的这个token
            message="退出成功";
            success= true;
        }
        CommonResponse res = new CommonResponse(success,message,null);
        return res;

    }

}
