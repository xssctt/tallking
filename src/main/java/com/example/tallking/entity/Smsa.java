package com.example.tallking.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("smsa")
public class Smsa {
    private Integer id;
    private String mobile;
    private String verificationKey;
    private String createtime;
}
