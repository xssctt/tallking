package com.example.tallking.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostListDto {



    List<Postdt> postDtoList;

    postSearchDto postSearchDto;

}
