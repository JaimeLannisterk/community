package com.springboot.community.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {
    @GetMapping("/index")
    public String hello(@RequestParam(name = "name",required = false,defaultValue = "world") String name, Model model){
        model.addAttribute("name","zhangsan");
        return  "index";
    }
}
