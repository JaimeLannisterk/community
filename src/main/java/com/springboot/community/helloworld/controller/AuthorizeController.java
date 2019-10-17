package com.springboot.community.helloworld.controller;

import com.springboot.community.helloworld.dto.AccessTokenDTO;
import com.springboot.community.helloworld.dto.GitHubUser;
import com.springboot.community.helloworld.mapper.UserMapper;
import com.springboot.community.helloworld.model.User;
import com.springboot.community.helloworld.provider.GithubProvider;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.rmi.transport.proxy.HttpReceiveSocket;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 *
 */
@Controller
public class AuthorizeController {
    @Autowired
    private  GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String redirectUri;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        ;
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);

        GitHubUser gitHubUser = githubProvider.getUser(accessToken);
        System.out.println(gitHubUser.toString());
        if(gitHubUser !=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setLogin(String.valueOf(gitHubUser.getLogin()));
            userMapper.insert(user);
            response.addCookie(new Cookie( "token",token));
           // request.getSession().setAttribute("user",gitHubUser);
            return "redirect:/";
        }else{
            return "redirect:/";
        }

    }
}
