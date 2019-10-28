package com.springboot.community.helloworld.controller;

import com.springboot.community.helloworld.dto.QuestionDTO;
import com.springboot.community.helloworld.mapper.QuestionMapper;
import com.springboot.community.helloworld.mapper.UserMapper;
import com.springboot.community.helloworld.model.Question;
import com.springboot.community.helloworld.model.User;
import com.springboot.community.helloworld.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
      Cookie[] cookies = request.getCookies();
      if(cookies!=null&&cookies.length!=0) {
             for(Cookie cookie:cookies){
              if(cookie.getName().equals("token")){
                  String token = cookie.getValue();
                  User user =userMapper.findByToken(token);
                  if(user !=null){
                      request.getSession().setAttribute("user",user);
                      break;
                  }
              }
            }
         }
        List<QuestionDTO> list = questionService.list();
        for (QuestionDTO q:list
             ) {
            q.setDescription("reset");

        }
        model.addAttribute("questions",list);
        //model.addAttribute("name","zhangsan");
        return  "index";
    }
}
