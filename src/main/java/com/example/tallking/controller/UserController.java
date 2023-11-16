package com.example.tallking.controller;


import com.example.tallking.common.JsonResult;
import com.example.tallking.common.Result;
import com.example.tallking.entity.*;
import com.example.tallking.service.SmsService;
import com.example.tallking.service.TokenService;
import com.example.tallking.service.UserService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {



    @Resource
    UserService userService;

    @Resource
    SmsService smsService;

    @Resource
    TokenService tokenService;

    //POST
    ///api/user/create
    //create
    @PostMapping("/create")
    public JsonResult<Tokendto> create(UserDto userdto){

        System.out.println(userdto.toString());
        String token=userService.createUser(userdto);


        Tokendto tokendto=Tokendto.builder().token(token).build();

//        Map<String,Object> TokenDto=new HashMap<>();
//        TokenDto.put("token",token);
//
//        Map<String,Object> map=new HashMap<>();
//        map.put("TokenDto",TokenDto);



        return  new JsonResult<>(tokendto);
    }


    //POST
    ///api/user/mobile
    //bindMobile
    @PostMapping("/mobile")
    public JsonResult bindMobile(Smsa smsa, String code){

        //

        String key= smsService.getCodeByIphone(smsa.getMobile());


        //



        return  new JsonResult();
    }


    //GET
    ///api/user/pwd
    //verifyPwd
    @GetMapping("/pwd")
    public JsonResult verifyPwd(String password,String token){

        //验证密码
        Tokendto tokenuser=tokenService.getUserIdByToken(token);

        UserDto user= userService.getUserByid(tokenuser.getUserid());
        if(user.getPassword().equals(password)){

            return  new JsonResult<>();
        }else {

            return  new JsonResult(false,"error");
        }


    }



    //POST
    ///api/user/update
    //update
    @PostMapping("/update")
    public JsonResult update(String mobile,String nickname,String oldpassword,String password,String token){


        //查找有没有该用户
       // Userdto userdto= userService.getUserIdByiphone(mobile);

        //根据nickname或者手机号  找到用户 修改密码

        Tokendto tokenuser=tokenService.getUserIdByToken(token);

        UserDto user= userService.getUserByid(tokenuser.getUserid());

        if(nickname.isEmpty()){

            if(oldpassword == null || password == null){
                throw new RuntimeException("kong");
            }

            //验证旧密码
            String password1=user.getPassword();
            if(password1.equals(oldpassword)){
                //修改密码
                userService.updatepasword(password,user.getId());

                return new JsonResult();
            }else {
                throw new RuntimeException("旧密码错误");
            }

        }else {
            userService.updatenickname(nickname,user.getId());
        }


        return new JsonResult();
    }




    //GET
    ///api/user/visitor
    //getVisitor
    @GetMapping("/visitor")
    public JsonResult<List<UserLoginDto>> getVisitor(String token){

        //
        Tokendto tokenuser=tokenService.getUserIdByToken(token);

        UserDto user= userService.getUserByid(tokenuser.getUserid());

        String avatar=user.getAvatar();
        if(avatar.startsWith("uploads")){
            avatar =avatar;
        }


        System.out.println(avatar);
//        Map<String,Object> UserLoginDto1=new HashMap<>();
//        UserLoginDto1.put("avatar",avatar);
//        UserLoginDto1.put("username",user.getUsername());
//
//        Map<String,Object> map=new HashMap<>();
//        map.put("UserLoginDto",UserLoginDto1);

        UserLoginDto userLoginDto=UserLoginDto.builder().avatar(avatar).username(user.getUsername()).build();
        List<UserLoginDto>  list=new ArrayList<>();
        list.add(userLoginDto);



        return  new JsonResult<>(list);
    }




    //GET
    ///api/user/whoami
    //getUser
    @GetMapping("/whoami")
    public JsonResult<User> getUser(String token){

        //token  scjuhahkzhoqedwx
//        Userdto user= UserContext.getCruuser();
//        Map<String,Object> datamap=new HashMap<>();
//        datamap.put("data",user);

        String[] urll={
                "http://xueba.it266.com:9002/default/avatar/0.jpeg",
                "http://xueba.it266.com:9002/default/avatar/1.jpeg",
                "http://xueba.it266.com:9002/default/avatar/2.jpeg",
                "http://xueba.it266.com:9002/default/avatar/3.jpeg",
                "http://xueba.it266.com:9002/default/avatar/4.jpeg",
                "http://xueba.it266.com:9002/default/avatar/5.jpeg",
                "http://xueba.it266.com:9002/default/avatar/6.jpeg",
                "http://xueba.it266.com:9002/default/avatar/7.jpeg",
                "http://xueba.it266.com:9002/default/avatar/8.jpeg"};
        //int a= (int) (Math.random()*9);
        int a = new Random().nextInt(9);

        Tokendto tokenuser=tokenService.getUserIdByToken(token);

        UserDto user= userService.getUserByid(tokenuser.getUserid());

        //如果没有  设置默认
        String avatar=user.getAvatar();
        if(avatar == null){
            // System.out.println("111111111");
            userService.updateavatar(urll[a],tokenuser.getUserid());
            user.setAvatar(urll[a]);
            UserContext.setCruuser(user);
        }

        //如果有  完整路径返回
        String avatar1=user.getAvatar();
        if(avatar1.startsWith("uploads")){
//            avatar1 ="http://localhost:8088/static/"+avatar1;
            avatar1 =avatar1;
        }

        User user1=User.builder()
                .username(user.getUsername())
                .avatar(avatar1)
                .id(user.getId())
                .createdAt(user.getCreatedat())
                .mobile(user.getMobile())
                .nickname(user.getNickname())
                .build();



//
//        Map<String,Object> data=new HashMap<>();
//        data.put("avatar",user.getAvatar());
//        data.put("createdAt",user.getCreatedat());
//        data.put("mobile",user.getMobile());
//        data.put("nickname",user.getNickname());
//        data.put("username",user.getUsername());
//        data.put("id",user.getId());

//        Map<String,Object> datamap=new HashMap<>();
//        datamap.put("UserDto",user);
//
//        UserDto user1=UserDto
//                .builder()
//                .avatar(user.getAvatar())
//                .id(user.getId())
//                .username(user.getUsername())
//                .createdat(user.getCreatedat())
//                .mobile(user.getMobile())
//                .nickname(user.getNickname())
//                .build();



        return new JsonResult<>(user1);
    }



}
