package com.hs.HealthKeepFit.dao;

import com.hs.HealthKeepFit.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    public List<User> selectAllUser();

    public User login(User user);

    public List<User> selectAllCoach();

}
