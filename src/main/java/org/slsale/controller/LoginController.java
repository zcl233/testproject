package org.slsale.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.log4j.Logger;
import org.slsale.common.Constants;
import org.slsale.common.RedisAPI;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.pojo.Menu;
import org.slsale.pojo.User;
import org.slsale.service.function.FunctionService;
import org.slsale.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class LoginController extends BaseController{
    private Logger logger=Logger.getLogger(LoginController.class);
    @Resource
    private UserService userService;
    @Resource
    private FunctionService functionService;
    @Resource
    private RedisAPI redisAPI;
    @RequestMapping("/main.html")
    public ModelAndView main(HttpSession session){
        logger.debug("main====================");
        User user = this.getCurrentUser();
        List<Menu> mList=null;
        if(null!=user){
            HashMap<String, Object> model = new HashMap<>();
            model.put("user",user);
            /**
             * key:menuList+roleID
             * value:mList
             */
            //redis里是否有数据
            if(!redisAPI.exist("menuList"+user.getRoleId())){
                //根据当前用户获取菜单列表mList
                        mList=getFuncByCurrentUser(user.getRoleId());
                //json
                if(null!=mList){
                    String jsonString = JSON.toJSONString(mList);
                    model.put("mList",jsonString);
                    System.out.println("redis========================"+redisAPI);
                    redisAPI.set("menuList"+user.getRoleId(),jsonString);
                }else{
                    //redis中有数据，直接从redis里取数据
                    String redisMenuListKeyString = redisAPI.get("menuList" + user.getRoleId());
                    logger.debug("redis中的menuList数据"+redisMenuListKeyString);
                    if(redisMenuListKeyString!=null&&!"".equals(redisMenuListKeyString)){
                        model.put("mList",redisMenuListKeyString);
                    }else{
                        return new ModelAndView("redirect:/");
                    }
                }
                session.setAttribute(Constants.SESSION_BASE_MODEL,model);
                return new ModelAndView("main",model);
            }

        }
        return new ModelAndView("redirect:/");
    }

    protected List<Menu> getFuncByCurrentUser(int roleId){
        ArrayList<Menu> menuList = new ArrayList<>();
        Authority authority = new Authority();
        authority.setRoleId(roleId);
        try {
            List<Function> mList = functionService.getMainFunctionList(authority);
            if(mList!=null){
                for (Function function:mList){
                    Menu menu = new Menu();
                    menu.setMainMenu(function);
                    function.setRoleId(roleId);
                    List<Function> subList = functionService.getSubFunctionList(function);
                    if(subList!=null){
                        menu.setSubMenus(subList);
                    }
                    menuList.add(menu);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return menuList;
    }
    @RequestMapping("/login.html")
    @ResponseBody
    public String login(HttpSession session, @RequestParam String user, HttpServletRequest request)  {

        if(user==null||"".equals(user)){
            return "nodata";
        }else{
            User user1 = JSON.parseObject(user, new TypeReference<User>() {});
//            User user1 = (User) JSON.parseObject(user);
            System.out.println("测试============================"+user1.getPassword());
            try {
                if(userService.loginCodeIsExit(user1)==0){
                    return "nologincode";

                }else {
                    User _user = userService.getLoginUser(user1);
                    if(null!=_user){
                        //当前用户存到session中
                        session.setAttribute(Constants.SESSION_USER,_user);
                        User updateLoginTimeUser = new User();
                        updateLoginTimeUser.setId(_user.getId());
                        updateLoginTimeUser.setLastLoginTime(new Date());
                        userService.modifyUser(updateLoginTimeUser);
                        updateLoginTimeUser=null;
                        System.out.println("返回了================success");
                        return "success";
                    }else{
                        return "pwderror";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "false";
            }

        }

    }

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout.html")
    public String logout(HttpSession session){
        session.removeAttribute(Constants.SESSION_USER);
        session.invalidate();
        this.setCurrentUser(null);
        return "index";
    }
}
