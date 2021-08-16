package com.chong.xinyuproject_new.controller;

import com.chong.xinyuproject_new.pojo.Test01;
import com.chong.xinyuproject_new.service.TestService;
import com.chong.xinyuproject_new.service.TestServiceImpl;
import com.chong.xinyuproject_new.utils.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 控制器 Controller层 --->service
 * @Controller ：处理器默认的是返回视图
 * @RestController:处理器返回的是字符串格式  @Responsebody
 *
 * @RequestMapping: 标注这个方法为处理器,默认可以接收所有的请求（get,post...）
 * : @GetMapping  @PostMapping  @PutMapping   @DeleteMapping。。。
 *
 * @AutoWired :
 * @Resource : 注入注解 --把Spring容器中的对象注入到这个属性中(1.把对应的类对象注入 2.注入实现了这个接口的类对象)
 *
 *
 *
 */

@RestController
public class TestController {

    @Resource
    private TestService testService;

//    @GetMapping("/getAll")
//    public String getAll(){
//        List<Test01> oList = testService.getAll();
//        return oList.toString();
//    }

    @GetMapping("/getAll")
    public CommonResponse getAll(){
        List<Test01> oList = testService.getAll();
        CommonResponse<List<Test01>> res = new CommonResponse<>();
        res.setSuccess(true);
        res.setMessage("查询成功");
        res.setContent(oList);
        return res;
    }


    /**
     * 添加
     * @param test01 :注意 从前端传递的参数名要和对象中的属性名一致，才可以映射成功
     * @return
     *
     * success:true
     * message:添加成功
     * content:[{tname:张三,tage:18},{},{}]  /   null
     *
     *
     */
//    @PostMapping("/add")
//    public String add(Test01 test01){
//        System.out.println("接收到的参数："+test01);
//        //调用service层的添加方法，返回int类型结果
//        int getRow=testService.add(test01);
//        if(getRow>0){//添加成功
//            return "添加成功";
//        }
//        return "添加失败";
//    }
    @PostMapping("/add")
    public CommonResponse add(Test01 test01){
        System.out.println("接收到的参数："+test01);
        //调用service层的添加方法，返回int类型结果
        int getRow=testService.add(test01);
        String message="";
        if(getRow>0){//添加成功
           message="添加成功";
        }else{
            message="添加失败";
        }
        //创建返回的对象
        CommonResponse res = new CommonResponse(true,message,null);
        return res;
    }

    @PostMapping("/update")
    public CommonResponse update(Test01 test01){//id:6 tname:null tage:25
        System.out.println(test01);
        int getRow = testService.update(test01);
        String message="";
        if(getRow>0){
            message="修改成功";
        }else{
            message="修改失败";
        }
        CommonResponse res = new CommonResponse(true, message, null);
        return res;
    }


    @GetMapping("/remove")
    public CommonResponse remove(Test01 test01){
        int getRow= testService.delete(test01);
        String message="";
        if(getRow>0){
            message="删除成功";
        }else{
            message="删除失败";
        }
        return new CommonResponse(true,message,null);
    }




    /**
     *1.添加一个用户
     * mapper接口中的抽象方法-->mapper.xml实现 -->service接口中的抽象方法 --->service实现类中的实现方法
     * --->controller中的处理器
     *
     *2.修改张无忌的年龄为30
     *3.删除张三这个用户
     *4.查询年龄在60岁以上的所有用户信息
     *
     */


}
