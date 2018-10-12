package org.slsale.controller;

import com.mysql.jdbc.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.mortbay.util.ajax.JSON;
import org.slsale.common.Constants;
import org.slsale.common.PageSupport;
import org.slsale.common.SQLTools;
import org.slsale.pojo.DataDictionary;
import org.slsale.pojo.Role;
import org.slsale.pojo.User;
import org.slsale.service.DataDictionary.DataDictionaryService;
import org.slsale.service.role.RoleService;
import org.slsale.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class UserController extends BaseController{
    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @RequestMapping("/backend/userlist.html")
    public ModelAndView userList(Model model, HttpSession session,
                                 @RequestParam(value = "currentpage",required = false)Integer currentpage,
                                 @RequestParam(value = "s_loginCode",required = false)String s_loginCode,
                                 @RequestParam(value = "s_referCode",required = false)String s_referCode,
                                 @RequestParam(value = "s_roleId",required = false)String s_roleId,
                                 @RequestParam(value = "s_isStart",required = false)String s_isStart){
        Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
        if(baseModel==null){
            return new ModelAndView("redirect:/");
        }else{
            //获取roleList and cardTypeList
            List<Role> roleList=null;
            DataDictionary dataDictionary=new DataDictionary();
            dataDictionary.setTypeCode("CARD_TYPE");
            List<DataDictionary> cardTypeList=null;
            try {
                roleList = roleService.getRoleList();
               cardTypeList= dataDictionaryService.getDataDictionaries(dataDictionary);
            } catch (Exception e) {
                e.printStackTrace();
            }
            User user = new User();
            if(null!=s_loginCode){
                user.setLoginCode("%"+SQLTools.transfer(s_loginCode)+"%");
            }
            if(null!=s_referCode){
                user.setReferCode("%"+SQLTools.transfer(s_referCode)+"%");
            }
            if(!StringUtils.isNullOrEmpty(s_isStart)){
                user.setIsStart(Integer.valueOf(s_isStart));
            }else{
                user.setIsStart(null);
            }

            if(!StringUtils.isNullOrEmpty(s_isStart)){
                user.setRoleId(Integer.valueOf(s_isStart));
            }else{
                user.setRoleId(null);
            }
            //page分页列表
            PageSupport page = new PageSupport();
            try {
                page.setTotalCount(userService.count(user));

            } catch (Exception e) {
                e.printStackTrace();
                page.setTotalCount(0);
            }
            if(page.getTotalCount()>0){
                if(currentpage!=null){
                    page.setPage(currentpage);
                }
                if(page.getPage()<=0){
                    page.setPage(1);
                }
                if(page.getPage()>page.getPageCount()){
                    page.setPage(page.getPageCount());
                }
                //mysql--分页查询
                user.setStarNum((page.getPage()-1)*page.getPageSize());
                user.setPageSize(page.getPageSize());
                List<User> userList=null;
                try {
                    userList=userService.getUserList(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    userList=null;
                    if(page==null){
                        page=new PageSupport();
                        page.setItems(null);
                    }
                }
                page.setItems(userList);
            }else {
                page.setItems(null);
            }
            model.addAllAttributes(baseModel);
            model.addAttribute("roleList",roleList);
            model.addAttribute("page",page);
            model.addAttribute("cardTypeList",cardTypeList);
            //为了数据的回显
            model.addAttribute("s_loginCode",s_loginCode);
            model.addAttribute("s_referCode",s_referCode);
            model.addAttribute("s_isStart",s_isStart);
            model.addAttribute("s_roleId",s_roleId);
            return new ModelAndView("/backend/userlist");
        }
    }
    @RequestMapping(value = "/backend/loadUserTypeList.html",produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public Object loadUserTypeList(@RequestParam(value = "s_roleId" ,required = false)String s_roleId){
        String jo="";
        try {
            DataDictionary dataDictionary=new DataDictionary();
            dataDictionary.setTypeCode("USER_TYPE");
            List<DataDictionary> userTypeList = dataDictionaryService.getDataDictionaries(dataDictionary);
            jo = JSON.toString(userTypeList);
            System.out.println("json数组："+userTypeList.get(0).getValueName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo;
    }
    @RequestMapping(value = "/backend/logincodeisexit.html",produces = {"text/html;charset:UTF-8"})
    @ResponseBody
    public String loginCodeIsExit(@RequestParam(value = "loginCode",required = false)String loginCode,
                                  @RequestParam(value = "id",required = false)String id){
        User user = new User();
        user.setLoginCode(loginCode);
        String result="failed";
        if(!id.equals("-1")){
            user.setId(Integer.valueOf(id));
        }
        try {
            if(userService.loginCodeIsExit(user)==0){
                result="only";
            }else{
                result="repeat";
            }
        }catch (Exception e){

        }
        return result;
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ModelAndView addUser(HttpSession session, @ModelAttribute("addUser")User addUser){
        if(session.getAttribute(Constants.SESSION_BASE_MODEL)==null){
            return new ModelAndView("redirect:/");
        }else{
            String idCard=addUser.getIdCard();
            String ps=idCard.substring(idCard.length()-6);
            addUser.setPassword(ps);
            addUser.setPassword2(ps);
            addUser.setCreateTime(new Date());
            addUser.setReferId(this.getCurrentUser().getId());
            addUser.setReferCode(this.getCurrentUser().getLoginCode());
            addUser.setLastUpdateTime(new Date());

            return new ModelAndView("redirect:/backend/userlist.html");
        }
    }
    @RequestMapping("/backend/upload.html")
    @ResponseBody
    public Object upload(@RequestParam(value = "a_fileInputID",required = false)MultipartFile cardFile,
                         @RequestParam(value = "a_fileInputBank",required = false)MultipartFile bankFile,
                         @RequestParam(value = "m_fileInputID",required = false)MultipartFile mcardFile,
                         @RequestParam(value = "m_fileInputBank",required = false)MultipartFile mBankFile,
                         @RequestParam(value = "loginCode",required = false)String loginCode,
                         HttpSession session, HttpServletRequest request){
       String path=request.getSession().getServletContext().getRealPath("statics"+File.separator
       +"uploadfiles");
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setTypeCode("PERSONALFILE_SIZE");
        List<DataDictionary> list=null;
        try {
            list=dataDictionaryService.getDataDictionaries(dataDictionary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int filesize=50000;
        if(list!=null){
            if(list.size()==1){
                filesize=Integer.valueOf(list.get(0).getValueName());
            }
        }
        if(cardFile!=null){
            String oldFileName=cardFile.getOriginalFilename();
            String prefix=FilenameUtils.getExtension(oldFileName);
            if(cardFile.getSize()>filesize){
                return "1";
            }else if(prefix.equalsIgnoreCase("jpg")||prefix.equalsIgnoreCase("png")
                    ||prefix.equalsIgnoreCase("jpeg")||prefix.equalsIgnoreCase("pneg")){
                //给文件进行重命名：系统毫秒数+100w以内的随机数
                Random random = new Random();
                String fileName=System.currentTimeMillis()+random.nextInt(1000000)+"_IDcard.jpg";
                File targetFile=new File(path,fileName);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                try {
                    cardFile.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String url=request.getContextPath()+"/statics/upload/"+fileName;
                return url;
            }else{
                return "2";
            }
        }
        return null;
    }

}
