package com.example.tallking.interceptor;


import com.example.tallking.entity.Tokendto;
import com.example.tallking.entity.UserContext;
import com.example.tallking.entity.UserDto;
import com.example.tallking.service.TokenService;
import com.example.tallking.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;


@Component
public class Tokeninterceptor implements HandlerInterceptor {

    @Resource
    UserService userService;
    @Resource
    TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token=request.getParameter("token");

        System.out.println(token);
        //token  userid
        if(token == null){
            throw new RuntimeException("token null");
        }

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
        int a = new Random().nextInt(9);

        Tokendto tokenuser=tokenService.getUserIdByToken(token);

        UserDto user= userService.getUserByid(tokenuser.getUserid());


        // 获取相对路径
        String avatar = user.getAvatar();

        // 拼接成绝对路径
        //String abavatar = "D:\\peixun\\jsp\\tallking\\" + avatar;

        if(avatar == null){
           // System.out.println("111111111");
            userService.updateavatar(urll[a],tokenuser.getUserid());
            user.setAvatar(urll[a]);
            UserContext.setCruuser(user);
            return true;
        }


        return true;
    }
}
