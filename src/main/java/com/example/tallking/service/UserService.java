package com.example.tallking.service;

import com.example.tallking.entity.Tokendto;
import com.example.tallking.entity.UserDto;
import com.example.tallking.mapper.TokendtoMapper;
import com.example.tallking.mapper.UserdtoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    TokenService tokenService;


    @Resource
    UserdtoMapper userdtoMapper;
    @Resource
    TokendtoMapper tokendtoMapper;

    //根据nickename

    public UserDto getByusername(String username){

        return userdtoMapper.getIdByname(username);
    }



    public UserDto getUserByid(int id){

        UserDto user=userdtoMapper.getuser(id);

        return user;
    }


    //根据手机号
    public UserDto getUserIdByiphone(String iphone){

        return userdtoMapper.getUserByiphone(iphone);
    }

//    public String updatepassword(String ){
//
//    }


//    public String updatepassword(){
//
//    }


    //更新密码
    public void updatepasword(String password,Integer id){

        userdtoMapper.updatePassword(password,id);
    }

    //更新头像
    public void updateavatar(String avatar,Integer id){

        userdtoMapper.updatAevatar(avatar,id);
    }

    //更新nickname
    public void updatenickname(String nickname,Integer id){

        userdtoMapper.updatnickname(nickname,id);
    }




    public String createUser(UserDto userdto){

        //先查询   根据username/nickname
        //后注册 给token   user1是否存在重复  user2插入
        String token=tokenService.getToken();

        UserDto user1=userdtoMapper.getIdByname(userdto.getUsername());
        System.out.println(user1);


        if(user1 != null){
            throw new RuntimeException("用户已存在");

        }else {

            //插入 user
            userdtoMapper.InserUser(userdto);

            UserDto user2=userdtoMapper.getIdByname(userdto.getUsername());
            System.out.println(user2);

            Tokendto tokenuser=Tokendto.builder().token(token).userid(user2.getId()).build();

            //token 保存
            tokendtoMapper.InserToken(tokenuser);



            return token;
        }

    }

}
