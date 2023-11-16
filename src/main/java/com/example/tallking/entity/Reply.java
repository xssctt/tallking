package com.example.tallking.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.persistence.Column;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@TableName("reply")
public class Reply {

    @TableId(value = "id",type = IdType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "postid")
    private Integer postid;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "content")
    private String content;



}
