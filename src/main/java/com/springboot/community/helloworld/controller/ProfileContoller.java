package com.springboot.community.helloworld.controller;

import com.springboot.community.helloworld.dto.PaginationDTO;
import com.springboot.community.helloworld.mapper.UserMapper;
import com.springboot.community.helloworld.model.User;
import com.springboot.community.helloworld.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileContoller {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action")String action, Model model,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5")Integer size){
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
        if(user==null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        PaginationDTO paginationDTO= questionService.list(user.getId(),page,size);
        model.addAttribute("pagination",paginationDTO);
        return  "profile";
    }
}
