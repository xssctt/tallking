package com.example.tallking.service;


import com.example.tallking.entity.Tokendto;
import com.example.tallking.mapper.TokendtoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class TokenService {
    @Resource
    TokendtoMapper tokendtoMapper;

    public String getToken(){


            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                char c = (char)('a' + random.nextInt(26));
                sb.append(c);
            }
            return sb.toString();


    }

    public String getTby(int userid){

        return tokendtoMapper.getTokenIdByuserid(userid);
    }

    public Tokendto getUserIdByToken(String token){

        return tokendtoMapper.getUserIdByToken(token);
    }


    public String gettokenIdByuserid(int userid){

        return tokendtoMapper.getTokenIdByuserid(userid);
    }





}
