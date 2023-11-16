package com.example.tallking.controller;

import com.example.tallking.common.JsonResult;
import com.example.tallking.common.Result;
import com.example.tallking.entity.Tokendto;
import com.example.tallking.entity.UserDto;
import com.example.tallking.service.TokenService;
import com.example.tallking.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Resource
    UserService userService;

    @Resource
    TokenService tokenService;
    //POST
    ///api/token/create
    //create
    @PostMapping("/create")
    public JsonResult<Tokendto> create(String password, String username){

        //根据username  获取 userid password
        UserDto user=userService.getByusername(username);
        String password1=user.getPassword();
        int userid=user.getId();
        //根据 userid 获取token 返回token

        if(password1.equals(password)){
            String token=tokenService.getTby(userid);

            Tokendto tokendto=Tokendto.builder().token(token).build();
//
//            Map<String,Object> TokenDto=new HashMap<>();
//            TokenDto.put("token",token);
//
//            Map<String,Object> map=new HashMap<>();
//            map.put("TokenDto",TokenDto);


            return  new JsonResult<>(tokendto);
        }else {
            throw new RuntimeException("password is error");
        }





    }



    //GET
    ///api/token/delete
    //delete
    @PostMapping("/delete")
    public JsonResult delete() {

        return new JsonResult();
    }

}
