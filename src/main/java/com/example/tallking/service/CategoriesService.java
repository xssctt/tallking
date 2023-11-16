package com.example.tallking.service;


import com.example.tallking.entity.Category;
import com.example.tallking.mapper.CategoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoriesService {

    @Resource
    CategoryMapper categoryMapper;
    //

    public PageInfo<Category> findPage(Integer pageNum, Integer pageSize, String name){
        //封装
        PageHelper.startPage(pageNum,pageSize);
        List<Category> list=categoryMapper.findByName(name);
        return PageInfo.of(list);

    }

    public Category findCategoryById(Integer id){
        //封装

        return categoryMapper.selectById(id);

    }
    public String findCategorynameById(Integer id){
        //封装

        return categoryMapper.selectById(id).getName();

    }



    public List<Category> Page(Integer pageNum, Integer pageSize){
        //    /pagenum  第几页 / pagesize 每页多少数据 /total_count 查询的总记录 / total_pages 总页数
        //    /offset = (page_num - 1) * page_size   需要跳过的数据数
        //
        //# 设置分页参数
        //page_size = 10
        //page_num = 1
        //
        //# 计算总页数
        // total_pages = total_count    --> page_size
        //if total_count % page_size != 0:
        //    total_pages += 1
        //
        //# 获取当前页数据
        //offset = (page_num - 1) * page_size

        //query = "SELECT id, title, content, userid FROM articles LIMIT (page_size) OFFSET (offset)"
        int totalCount=categoryMapper.findByNameall();
        Integer offset;

        Integer totalPages = totalCount;
        if (totalCount % pageSize != 0){
            totalPages += 1;
        }
     //   # 获取当前页数据
        offset = (pageNum - 1) * pageSize;


        List<Category> list=categoryMapper.findByNamep(offset,pageSize);


        return list;

    }


    public List<Category> findall(){

        List<Category> list=categoryMapper.findallData();

        return list;

    }



}
