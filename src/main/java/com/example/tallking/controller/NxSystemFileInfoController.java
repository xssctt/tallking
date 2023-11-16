package com.example.tallking.controller;


import cn.hutool.core.io.FileUtil;

import com.example.tallking.common.JsonResult;
import com.example.tallking.common.Result;
import com.example.tallking.entity.UserDto;
import com.example.tallking.service.NxSystemFileInfoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件增删改查
 */

@RestController
@RequestMapping(value = "/files")
public class NxSystemFileInfoController {

    //文件存储位置  System.getProperty("user.dir")获得主目录
    private static final String BASE_PATH=System.getProperty("user.dir")+"/src/main/resources/static/file/";

    @Resource
    NxSystemFileInfoService nxSystemFileInfoService;



    /**
     * 上传
     */
    @PostMapping("/upload")
    public JsonResult upload(MultipartFile file) throws IOException {

        //
        String originalName=file.getOriginalFilename();

        System.out.println(file);

        //
        if (originalName==null){
            return new JsonResult(false,"文件不能为空");
        }
        //
        if ( !originalName.contains("png") && !originalName.contains("jpg") && !originalName.contains("gif") && !originalName.contains("jpeg")){
            return new JsonResult(false,"只能上传图片");
        }

        //文件加时间戳   返回主文件名 + 当前系统时间 + 获取文件扩展名（后缀名）
        String fileName= FileUtil.mainName(originalName)+System.currentTimeMillis()+"."+FileUtil.extName(originalName);
        //文件上传
        FileUtil.writeBytes(file.getBytes(),BASE_PATH+fileName);


        //信息入库
        UserDto info=new UserDto();
//        info.setOriginname(originalName);
//        info.setFilename(fileName);

        System.out.println(info);

       // NxSystemFileInfo addinfo=nsSystemFileInfoService.add(info);

//        if(addinfo!=null){
//            return Result.success(addinfo);
//        }


        return new JsonResult(false,"上传失败");
    }


    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable long id){

     //   nsSystemFileInfoService.delete(id);

        return new JsonResult();
    }
    /**
     * 根据id查询
     */
//    @GetMapping("/{id}")
//    public Result detail(@PathVariable long id){
//
//        return Result.success(nsSystemFileInfoService.findById(id));
//
//    }

    /**
     *
     * 下载文件
     */
    @GetMapping("/download/{id}")
    public void download(@PathVariable String id, HttpServletResponse response) throws IOException {

//        if (StrUtil.isBlank(id) || "null".equals(id)){
//            throw new IOException("1001","未上传文件");
//        }

       // NxSystemFileInfo nxSystemFileInfo=nsSystemFileInfoService.findById(Long.parseLong(id));

//        if (nxSystemFileInfo==null){
//            throw new CustomException("1001","没有该文件");
//        }


        //读取文件
       // byte[] bytes=FileUtil.readBytes(BASE_PATH+nxSystemFileInfo.getFilename());

//        response.reset();
//        response.addHeader("Content-Disposition","attachment;filename="+
//                URLEncoder.encode(nxSystemFileInfo.getOriginname(),"UTF-8"));
//
//        response.addHeader("Content-Length",""+bytes.length);
//
//        //BufferedOutputStream  流转换  OutputStream   getOutputStream
//        OutputStream toClient=new BufferedOutputStream(response.getOutputStream());
//        response.setContentType("application/octet-stream");
//
//        toClient.write(bytes);
//        toClient.flush();
//        toClient.close();
    }
}

