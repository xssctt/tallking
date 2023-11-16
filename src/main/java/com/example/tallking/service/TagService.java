package com.example.tallking.service;


import com.example.tallking.entity.Tag;
import com.example.tallking.mapper.TagMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagService {

    @Resource
    TagMapper tagMapper;

    public Tag findTagByname(String tagname){


        return tagMapper.findTagByname(tagname);
    }


    public List<Tag> findTagHot(Integer hotnum){

        return tagMapper.findTagHot(hotnum);
    }


    public Tag findTagById(Integer tagid) {
        return tagMapper.selectById(tagid);
    }
}
