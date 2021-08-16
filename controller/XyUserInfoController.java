package com.chong.xinyuproject_new.controller;

import com.chong.xinyuproject_new.pojo.XyUserInfo;
import com.chong.xinyuproject_new.resquest.UpdatePsdRequest;
import com.chong.xinyuproject_new.service.XyUserInfoService;
import com.chong.xinyuproject_new.utils.CommonResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户控制层
 *
 */
@RestController
@RequestMapping("/userinfo")
//@CrossOrigin  //这个注解是解决前端页面访问该controller接口时的跨域问题
public class XyUserInfoController {

    @Resource
    private XyUserInfoService xyUserInfoService;

    @PostMapping("/login")
    //@RequestBody的作用就是把前端传递过来的json字符串格式转换为对象格式
    public CommonResponse login(@RequestBody XyUserInfo xyUserInfo) {

        String message=null;
        boolean success=false;
        XyUserInfo loginUserInfo=null;
        if (xyUserInfo.getUserName() != null && !xyUserInfo.getUserName().equals("")
                && xyUserInfo.getUserPsd() != null && !xyUserInfo.getUserPsd().equals("")) {
            //用户名和密码不为空
            //调用service层的登陆功能，接收返回的对象值
            loginUserInfo = xyUserInfoService.login(xyUserInfo);
            if(loginUserInfo==null){//登陆失败
                message="登陆失败,用户名或密码错误！";

            }else{//登陆成功
                message="登陆成功！";
                success=true;
            }
        }else{
            //用户名或密码为空
            message="登陆失败,用户名或密码不能为空";
        }
        CommonResponse<XyUserInfo> res = new CommonResponse<>(success,message,loginUserInfo);
        return res;
    }

    @PostMapping("/add")
    public CommonResponse add(XyUserInfo xyUserInfo) {

        String message="";
        if (xyUserInfo.getUserName() != null && !xyUserInfo.getUserName().equals("")
                && xyUserInfo.getUserPsd() != null && !xyUserInfo.getUserPsd().equals("")) {
            //用户名和密码不为空
            //调用service层的登陆功能，接收返回的对象值
            int  getRow=xyUserInfoService.addUserInfo(xyUserInfo);
            if(getRow==0){//登陆失败
                message="添加失败！";

            }else{//登陆成功
                message="添加成功";
            }
        }else{
            //用户名或密码为空
            message="添加失败,用户名或密码不能为空";
        }
        CommonResponse res = new CommonResponse(true,message,null);
        return res;
    }

    @PostMapping("/updatePsd")
    public CommonResponse  updatePsd(@RequestBody UpdatePsdRequest psdRequest){
        //1.校验user_name中的这个旧密码是否正确
        XyUserInfo loginUser= xyUserInfoService.login(psdRequest);
        String message="";
        if(loginUser != null){
            //2.修改密码
            loginUser.setUserPsd(psdRequest.getNewPsd());
            int getRow= xyUserInfoService.update(loginUser);
            if(getRow>0){
                message="密码修改成功";
            }else{
              //密码修改失败
                message="密码修改失败 ";
            }
        }else{
            //原始密码有误
            message="原始密码有误";
        }
        CommonResponse res = new CommonResponse(message,null);
       return res;
    }

    @PostMapping("/updateUserInfo")
    public CommonResponse  updateUserInfo(@RequestBody XyUserInfo xyUserInfo){
        String message="修改失败";
        boolean success=false;
        int getRow= xyUserInfoService.update(xyUserInfo);
        if(getRow>0){
            message="修改成功";
            success=true;
        }
        CommonResponse res = new CommonResponse(success,message,null);
        return res;
    }

    @GetMapping("/getUserInfoCount")
    public CommonResponse getUserInfoCount(){
        int count=xyUserInfoService.getUserInfoCount();
        CommonResponse res = new CommonResponse("查询成功",count);
        return res;
    }

    @GetMapping("/getUserInfoByLimit")
    public CommonResponse getUserInfoByLimit(){
       List<XyUserInfo> oList= xyUserInfoService.getUserInfoByLimit();
       CommonResponse res =new CommonResponse("成功",oList);
        return res;
    }

    //前置通知
    @GetMapping("/getUserInfoAll")
    public CommonResponse getUserInfoAll(){
        //环绕通知的开始
        List<XyUserInfo> oList= xyUserInfoService.getUserInfoAll();
        CommonResponse res = new CommonResponse(true,"查询成功",oList);
       //环绕通知的结束
        return res;
        //后置通知
    }


    @GetMapping("/removeUser")
    public CommonResponse removeUser(Integer user_id){
        String message="删除失败";
        boolean success=false;
       int getRow= xyUserInfoService.removeUserInfo(user_id);
       if(getRow>0){
           message="删除成功";
           success=true;
       }

       CommonResponse res = new CommonResponse(success,message,null);
        return res;
    }

    @GetMapping("/getUserInfo")
    public CommonResponse getUserInfo(Integer userId){
        String message="查询失败";
        boolean success =false;
        XyUserInfo user= xyUserInfoService.getUserInfo(userId);
        if(user != null ){
            message="查询成功";
            success=true;
        }
        CommonResponse res = new CommonResponse(success,message,user);
        return res;
    }


    /***
     * 1.修改密码（校验原始密码是否正确）
     * 2.删除用户
     * 3.查询所有用户
     * 4.查询所有用户的数量(注意mapper.xml中的返回值类型)
     * 5.查询1-5条用户数据
     *
     * 要求：
     * 1.mapper--service--controller
     * 2.单独测试mapper
     *
     *
     */


}
