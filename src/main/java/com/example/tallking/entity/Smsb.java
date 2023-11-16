package com.example.tallking.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("smsb")
public class Smsb {
    private Integer id;
    private String verificationKey;
    private String verificationCode;
}
