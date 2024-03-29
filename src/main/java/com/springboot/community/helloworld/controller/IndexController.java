package com.springboot.community.helloworld.controller;

import com.springboot.community.helloworld.dto.PaginationDTO;
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
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        User user =null;
      Cookie[] cookies = request.getCookies();
      if(cookies!=null&&cookies.length!=0) {
             for(Cookie cookie:cookies){
              if(cookie.getName().equals("token")){
                  String token = cookie.getValue();
                  user =userMapper.findByToken(token);
                  if(user !=null){
                      request.getSession().setAttribute("user",user);
                      break;
                  }
              }
            }
         }
/*      if(user==null){
          return "redirect:/";
      }*/
        PaginationDTO paginationDTO = questionService.list(page,size);
        model.addAttribute("pagination",paginationDTO);
        //model.addAttribute("name","zhangsan");
        return  "index";
    }
}
