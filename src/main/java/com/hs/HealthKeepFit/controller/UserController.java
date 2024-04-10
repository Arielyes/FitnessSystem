package com.hs.HealthKeepFit.controller;

import com.hs.HealthKeepFit.bean.Result;
import com.hs.HealthKeepFit.bean.User;
import com.hs.HealthKeepFit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Result result;

    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username, String password, HttpSession session) {
        try {
            User user=userService.login(username,password);
            session.setAttribute("user",user);
            result.setStatue(true);
        }
        catch (Exception e){
            result.setStatue(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/selectAllUser")
    @ResponseBody
    public Result selectAllUser(){
        try {
            List<User> list=userService.selectAllUser();
            result.setStatue(true);
            result.setList(list);
        }
        catch (Exception e){
            result.setStatue(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/selectAllCoach")
    @ResponseBody
    public Result selectAllCoach(){
        try{
            List<User> list =  userService.selectAllCoach();
            result.setStatue(true);
            result.setList(list);
        }catch (Exception e){
            result.setStatue(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
