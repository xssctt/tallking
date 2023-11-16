package com.example.tallking.service;


import com.example.tallking.entity.Smsa;
import com.example.tallking.entity.Smsb;
import com.example.tallking.entity.UserDto;
import com.example.tallking.mapper.SmsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

@Service
public class SmsService {


    public String randomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            char c = (char)('a' + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }


    @Resource
    SmsMapper smsMapper;


    //生成32 key 以及 code  iphone 11  21

     public Boolean Iscode(UserDto userdto, String code){
         Smsa smsa=smsMapper.getkeyByiphone(userdto.getMobile());
         Long codetime= Long.valueOf(smsa.getCreatetime());
         Calendar now=new GregorianCalendar();
         long nowtime=now.getTime().getTime();

         if( (nowtime-codetime) > 10*60*1000){
             throw new RuntimeException("code 已过期");
         }else {

             String kcode=smsMapper.getcodeBykey(smsa.getVerificationKey());
             if(kcode.equals(code)){
                 return true;
             }else {
                 throw new RuntimeException("code is error");
             }

         }

     }



     public String getCodeByIphone(String iphone){


         SmsService smsService=new SmsService();

         //key
         String key=smsService.randomString();

         //code
         int code= (int) (Math.random()*10000);
         String codep= String.valueOf(code);

         Calendar now=new GregorianCalendar();
         String time= String.valueOf(now.getTime().getTime());

         Smsa smsa=Smsa.builder().mobile(iphone).verificationKey(key).createtime(time).build();

         Smsb smsb=Smsb.builder().verificationKey(key).verificationCode(codep).build();

         smsMapper.insertSma(smsa);
         smsMapper.insertSmb(smsb);



         System.out.println(smsa);
         System.out.println(smsb);

         System.out.println("code :"+code);

          return key;

     }


}
