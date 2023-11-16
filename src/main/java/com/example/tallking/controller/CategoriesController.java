package com.example.tallking.controller;


import com.example.tallking.common.JsonResult;
import com.example.tallking.common.Result;
import com.example.tallking.entity.Category;
import com.example.tallking.service.CategoriesService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class CategoriesController {

    @Resource
    CategoriesService categoriesService;
    //GET
    // /api/post/categories
    //categories
    @GetMapping("/categories")
    public JsonResult<List<Category>> categorie(){

        return new JsonResult<>(categoriesService.findall());
    }


    @GetMapping("/categorie")
    public JsonResult categories(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "10") Integer pageSize){
        List<Category> list=categoriesService.Page(pageNum,pageSize);

        Map<String,Object> datalist=new HashMap<>();
        datalist.put("Category",list);

        return new JsonResult(datalist);
    }



    @GetMapping("/categories/{name}")
    public JsonResult<PageInfo<Category>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @PathVariable String name){
        return new JsonResult<>(categoriesService.findPage(pageNum,pageSize,name));
    }



    //GET
    // /api/post/categoryId
    //categoryId
    @GetMapping("/categoryId")
    public JsonResult categoryId(Integer id){

        return new JsonResult(categoriesService.findCategorynameById(id));
    }




}
