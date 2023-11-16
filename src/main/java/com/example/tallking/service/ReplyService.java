package com.example.tallking.service;

import com.example.tallking.entity.Reply;
import com.example.tallking.mapper.ReplyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReplyService {

    @Resource
    ReplyMapper replyMapper;

    public void inserreply(Reply reply){
        replyMapper.insert(reply);
    }


    public List<Reply> findReplyById(Integer id,Integer limit,Integer page) {

        int totalCount=replyMapper.selectcountByPostId(id);
        Integer offset;



        Integer totalPages = totalCount;
        if (totalCount % limit != 0){
            totalPages += 1;
        }
        //   # 获取当前页数据
        offset = (page - 1) * limit;

        System.out.println(id+"--"+limit+"--"+offset);

        return replyMapper.selectByPostlist(id,limit,offset);
    }
}
