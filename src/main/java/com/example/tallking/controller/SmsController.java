package com.example.tallking.controller;

import com.example.tallking.common.JsonResult;
import com.example.tallking.common.Result;
import com.example.tallking.entity.Smsa;
import com.example.tallking.service.SmsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Resource
    SmsService smsService;
    //POST
    ///api/sms/create
    //sendSms
    @PostMapping("/create")
    public JsonResult sendSms(Smsa smsa, String code){


       String key= smsService.getCodeByIphone(smsa.getMobile());
        Map<String,Object> map=new HashMap<>();
        map.put("key",key);

        return new JsonResult();
    }

}
