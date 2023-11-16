package com.example.tallking.mapper;

import com.example.tallking.entity.Smsa;
import com.example.tallking.entity.Smsb;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsMapper {

    @Insert("insert into smsa(verificationKey,mobile,createtime) values( #{Smsa.verificationKey},#{Smsa.mobile},#{Smsa.createtime} ) ")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertSma(@Param("Smsa") Smsa smsa);

    @Insert("insert into smsb(verificationKey,verificationCode) values( #{Smsb.verificationKey},#{Smsb.verificationCode} ) ")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertSmb(@Param("Smsb") Smsb smsb);

    @Select("select * from smsa where mobile=#{mobile} ORDER BY createtime DESC limit 1")
    Smsa getkeyByiphone(@Param("iphone") String mobile);


    @Select("select verificationCode from smsb where verificationKey=#{key}")
    String getcodeBykey(@Param("key") String key);

}
