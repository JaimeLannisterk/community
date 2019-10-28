package com.springboot.community.helloworld.dto;

import com.springboot.community.helloworld.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
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
    private User user;
}
