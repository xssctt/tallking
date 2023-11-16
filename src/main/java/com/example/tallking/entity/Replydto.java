package com.example.tallking.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Replydto {

    List<Postdt> replyList;
    Pagination pagination;
}
