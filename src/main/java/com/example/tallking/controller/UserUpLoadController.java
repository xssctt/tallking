package com.example.tallking.controller;


import cn.hutool.core.io.FileUtil;
import com.example.tallking.common.JsonResult;
import com.example.tallking.common.Result;
import com.example.tallking.entity.Tokendto;
import com.example.tallking.entity.UserDto;
import com.example.tallking.execption.CustomException;
import com.example.tallking.service.TokenService;
import com.example.tallking.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@RequestMapping("/api/upload")
@RestController
public class UserUpLoadController {

    //文件存储位置  System.getProperty("user.dir")获得主目录
    private static final String BASE_PATH=System.getProperty("user.dir")+"/src/main/resources/static/file/";
    @Value("${upload.path}")
    private String PATH_AVTR;

    @Resource
    TokenService tokenService;
    @Resource
    UserService userService;

    //POST
    //   api/upload/avatar
    //  avatar
    //头像上传
    @PostMapping("/avatar")
    public JsonResult avatar(@RequestParam("avatar") MultipartFile avatar,@RequestParam("token") String token) throws IOException {


        int index=  avatar.getOriginalFilename().lastIndexOf(".");
        String extname=avatar.getOriginalFilename().substring(index+1).toLowerCase(Locale.ROOT);

        String allImgFormat="png,jpg,jpeg,gif";

        if (!allImgFormat.contains(extname)){
            throw new IOException("只允许上传图片");
        }

        System.out.println(avatar);



        //路径
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/");
        String subPath=simpleDateFormat.format(new Date());


        //文件名称
        String savename= subPath+ UUID.randomUUID().toString().replaceAll("-","")+"."+extname;
        String uploadPath="uploads/";

        //文件夹
        File dir=new File(uploadPath+subPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        //这行代码的作用是构造上传文件保存的路径
        File save=new File(uploadPath+savename);

        Tokendto tokenuser=tokenService.getUserIdByToken(token);

        UserDto user= userService.getUserByid(tokenuser.getUserid());

        user.setAvatar(uploadPath+savename);
        userService.updateavatar("http://localhost:8088/static/"+uploadPath+savename,tokenuser.getUserid());
        System.out.println(uploadPath+savename);

        //上传的文件保存到指定路径中。
        avatar.transferTo(save.getAbsoluteFile());

        return new JsonResult();
    }

    //查看头像
    @GetMapping("/look")
    public String lookavatar(String token){


        Tokendto tokenuser=tokenService.getUserIdByToken(token);

        UserDto user= userService.getUserByid(tokenuser.getUserid());


        if (user.getAvatar()==null){
            throw new CustomException("1001","没有该文件");
        }


        // 获取相对路径
        String relativePath = user.getAvatar();

// 拼接成绝对路径
        String absolutePath = "D:\\peixun\\jsp\\tallking\\" + relativePath;
        System.out.println(absolutePath);
        return absolutePath;
    }




    //头像下载
    @GetMapping("/download")
    public void download(HttpServletResponse response,String token) throws IOException {

//        if (StrUtil.isBlank(id) || "null".equals(id)){
//            throw new IOException("1001","未上传文件");
//        }



        Tokendto tokenuser=tokenService.getUserIdByToken(token);

        UserDto user= userService.getUserByid(tokenuser.getUserid());


        if (user.getAvatar()==null){
            throw new CustomException("1001","没有该文件");
        }


        // 获取相对路径
        String relativePath = user.getAvatar();

// 拼接成绝对路径
        String absolutePath = "D:\\peixun\\jsp\\tallking\\" + relativePath;

// 读取文件
        byte[] bytes= FileUtil.readBytes(absolutePath);



//        //读取文件
//        byte[] bytes= FileUtil.readBytes(user.getAvatar());

        response.reset();
        response.addHeader("Content-Disposition","attachment;filename="+
                URLEncoder.encode("aaa","UTF-8"));

        response.addHeader("Content-Length",""+bytes.length);

        //BufferedOutputStream  流转换  OutputStream   getOutputStream
        OutputStream toClient=new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");

        toClient.write(bytes);
        toClient.flush();
        toClient.close();
    }








    @PostMapping("/avatartp")
    public JsonResult avatartp(@RequestParam("file") MultipartFile file, String token) throws IOException {


        int index=  file.getOriginalFilename().lastIndexOf(".");
        String extname=file.getOriginalFilename().substring(index+1).toLowerCase(Locale.ROOT);

        String allImgFormat="png,jpg,jpeg,gif";

        if (!allImgFormat.contains(extname)){
            throw new IOException("只允许上传图片");
        }

        System.out.println(file);





        //文件夹  有问题
        File dir=new File(BASE_PATH);
        if(!dir.exists()){
            dir.mkdirs();
        }

        String savename=UUID.randomUUID().toString().replaceAll("-","")+"."+extname;
        //文件上传
        FileUtil.writeBytes(file.getBytes(),BASE_PATH+savename);


        Tokendto tokenuser=tokenService.getUserIdByToken(token);

        UserDto user= userService.getUserByid(tokenuser.getUserid());

        user.setAvatar(BASE_PATH+savename);
        userService.updateavatar(BASE_PATH+savename,tokenuser.getUserid());
        System.out.println(BASE_PATH+savename);

        //上传的文件保存到指定路径中。

        return new JsonResult();
    }



}
