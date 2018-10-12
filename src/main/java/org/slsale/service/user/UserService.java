package org.slsale.service.user;

import org.slsale.pojo.User;

import java.util.List;


public interface UserService {
    /**
     * 登录
     * @param user
     * @return
     * @throws Exception
     */
    public User getLoginUser(User user) throws Exception;

    /**
     * 用户注册
     * @param user
     * @return
     * @throws Exception
     */
    public int addUser(User user) throws Exception;

    /**
     * loginCodeIsExit
     * @param user
     * @return
     * @throws Exception
     */
    public int loginCodeIsExit(User user)throws Exception;
    /**
     * modifyUser
     * @param user
     * @return
     * @throws Exception
     */
    public int modifyUser(User user)throws Exception;
    /**
     * 获取总数量
     * @param user
     * @return
     * @throws Exception
     */
    public int count(User user)throws  Exception;

    public List<User> getUserList(User user)throws Exception;


}
