package com.chong.xinyuproject_new.controller;

import com.chong.xinyuproject_new.pojo.Category;
import com.chong.xinyuproject_new.service.CategoryService;
import com.chong.xinyuproject_new.utils.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/getList")
    public CommonResponse getList(){

        List<Category> oList= categoryService.queryAll();
        CommonResponse res = new CommonResponse(true,"查询成功",oList);
        return res;

    }

}
