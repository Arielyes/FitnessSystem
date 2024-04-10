package com.hs.HealthKeepFit.controller;

import com.hs.HealthKeepFit.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UrlController {
    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();//session失效
        return "login";
    }

    @RequestMapping("/index.html")
    public String index(HttpSession session){
        User user= (User) session.getAttribute("user");
        if(user==null){
            return "login";
        }else {
            return "index";
        }
    }

    @RequestMapping("/userM.html")
    public String userM(){
        return "userM";
    }

    @RequestMapping("/subM.html")
    public String subM(){
        return "subM";
    }

    @RequestMapping("/devM.html")
    public String devM(){
        return "devM";
    }

    @RequestMapping("/addSub.html")
    public String addSub(){
        return "addSub";
    }

}
