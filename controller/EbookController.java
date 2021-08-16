package com.chong.xinyuproject_new.controller;

import com.chong.xinyuproject_new.pojo.Ebook;
import com.chong.xinyuproject_new.resquest.QueryEbookRequest;
import com.chong.xinyuproject_new.service.EbookService;
import com.chong.xinyuproject_new.utils.CommonResponse;
import com.chong.xinyuproject_new.utils.PageResponse;
import com.chong.xinyuproject_new.utils.SnowFlake;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
//@CrossOrigin  解决跨域问题
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;
    @Resource
    private SnowFlake snowFlake;

    @GetMapping("/getList")
    public CommonResponse getList(QueryEbookRequest ebookRequest){
        List<Ebook> oList= ebookService.getEBookList(ebookRequest);
        CommonResponse res = new CommonResponse(true,"查询成功",oList);
        return res;
    }

    /**
     * 分页查询
     * @param ebookRequest
     * @Validated :让校验器生效
     * @return
     */
    @GetMapping("/getListByPage")
    public CommonResponse getListByPage(@Validated QueryEbookRequest ebookRequest){

        //调用带分页的方法
        PageResponse pageRes= ebookService.getListByPage(ebookRequest);
        CommonResponse res = new CommonResponse(true,"查询成功",pageRes);
        return res;
    }


    @PostMapping("/save")
    public CommonResponse save(@RequestBody Ebook ebook){
        String message="失败";
        boolean success=false;
        int getRow=-1;
        //isEmpty(对象|属性)：判断是否为null  为空   undefind
        if(ObjectUtils.isEmpty(ebook.getId())){//如果id为空
            //添加功能
            long id= snowFlake.nextId();//就可以得到一个唯一的随机数id
            ebook.setId(id);
            getRow= ebookService.add(ebook);
            if(getRow>0){
                success=true;
                message="添加成功";
            }
        }else{
            //修改功能
            getRow=ebookService.update(ebook);
            if(getRow>0){
                success=true;
                message="修改成功";
            }
        }


        CommonResponse res = new CommonResponse(success,message,null);
        return res;
    }

    @GetMapping("/delete")
    public CommonResponse delete(long id){
        String message="删除失败";
        boolean success=false;
        if(id>0){
            int getRow= ebookService.delete(id);
            if(getRow>0){
                message="删除成功";
                success=true;
            }
        }else{
           message="删除失败，id不能为空";
        }
        CommonResponse res = new CommonResponse(success,message,null);
        return res;
    }


}
