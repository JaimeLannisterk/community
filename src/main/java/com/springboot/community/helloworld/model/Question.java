package com.springboot.community.helloworld.model;

import lombok.Data;

@Data
public class Question {
    private int id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate ;
    private Long gmtModified;
    private int creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
