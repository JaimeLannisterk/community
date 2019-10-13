package com.springboot.community.helloworld.controller;

import com.springboot.community.helloworld.dto.AccessTokenDTO;
import com.springboot.community.helloworld.dto.GitHubUser;
import com.springboot.community.helloworld.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Controller
public class AuthorizeController {
    @Autowired
    private  GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        ;
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("d6a635583232f2981b6e");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setClient_secret("396fcb0a7ee9ae952c02cc05ce7ad67cb8894f2c");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.toString());
        return "index";
    }

}
