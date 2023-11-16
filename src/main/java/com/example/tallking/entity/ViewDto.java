package com.example.tallking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewDto {
    Postdto postdto;
    List<Tag> tagList;
    Boolean favorite;
    Boolean like;
}
