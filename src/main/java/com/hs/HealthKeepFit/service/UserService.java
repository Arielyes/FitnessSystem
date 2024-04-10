package com.hs.HealthKeepFit.service;

import com.hs.HealthKeepFit.bean.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface UserService {
    public User login(String username,String password);

    public List<User> selectAllUser();

    public List<User> selectAllCoach();


}
