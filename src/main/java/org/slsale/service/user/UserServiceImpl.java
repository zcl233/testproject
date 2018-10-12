package org.slsale.service.user;

import org.slsale.dao.user.UserMapper;
import org.slsale.pojo.User;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User getLoginUser(User user) throws Exception {
        return userMapper.getLoginUser(user);
    }

    @Override
    public int addUser(User user) throws Exception {
        return userMapper.addUser(user);
    }

    @Override
    public int loginCodeIsExit(User user) throws Exception {
        System.out.println("usermapper============"+userMapper);
        return userMapper.loginCodeIsExit(user);
    }

    @Override
    public int modifyUser(User user) throws Exception {
        return userMapper.modifyUser(user);
    }

    @Override
    public int count(User user) throws Exception {
        return userMapper.count(user);
    }

    @Override
    public List<User> getUserList(User user) throws Exception {
        return userMapper.getUserList(user);
    }

}
