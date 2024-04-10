package com.hs.HealthKeepFit.service;

import com.hs.HealthKeepFit.bean.User;
import com.hs.HealthKeepFit.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        if (username.equals("")){
            throw new RuntimeException("账号不能为空");
        }

        if (password.equals("")){
            throw new RuntimeException("密码不能为空");
        }

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        user=userDao.login(user);
        if(user==null){
            throw new RuntimeException("账号或密码输入有误");
        }

        return user;



    }

    @Override
    public List<User> selectAllUser() {
        List<User> list=userDao.selectAllUser() ;
        if(list==null||list.size()==0){
            throw new RuntimeException("暂无教练与学员");
        }
        return list;
    }

    @Override
    public List<User> selectAllCoach() {
        List<User> list = userDao.selectAllCoach();
        //将所有用户的出生日期取出 -- 获取系统当前时间 -- 计算 -- 将计算结果注入到集合中
        if(list.size() == 0 || list == null){
            throw new RuntimeException("暂无教练！");
        }
        return list;
    }
}
