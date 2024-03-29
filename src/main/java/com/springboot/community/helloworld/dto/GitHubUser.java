package com.springboot.community.helloworld.dto;

import lombok.Data;

@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String login;
    private String avatar_url;
}
