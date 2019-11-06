package hao.you.controller;

import hao.you.domain.User;
import hao.you.enums.LoginCheckEnum;
import hao.you.service.LoginService;
import hao.you.service.UserService;
import hao.you.utils.ToolMethodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //注册功能
    @PostMapping("users")
    @ResponseBody
    public String addUser(User user, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        List<User> allUser = userService.getAllUser();
        if(ToolMethodUtil.isNull(user.getUser_name())||ToolMethodUtil.isNull(user.getUser_password())){
            return LoginCheckEnum.ACCOUNT_VERIFICATION.getName();
        }
        for(User userList:allUser){
            if(user.getUser_name().equals(userList.getUser_name())){
                return LoginCheckEnum.USER_EXIST.getName();
            }
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setUser_record_date(sf.format(new Date()));
        userService.addUser(user);
        return LoginCheckEnum.REGISTERED_SUCCESS.getName();
    }

    //登录功能
    @GetMapping("users")
    @ResponseBody
    public String login_fn(User user,HttpSession session,HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        if(ToolMethodUtil.isNull(user.getUser_name())||ToolMethodUtil.isNull(user.getUser_password())){
            return LoginCheckEnum.ACCOUNT_VERIFICATION.getName();
        }
        User query_user = userService.getUserByName(user.getUser_name());
        if(ToolMethodUtil.isNull(query_user)){
            return LoginCheckEnum.ACCOUNT_NOT_EXIST.getName();
        }else{
            if(!user.getUser_password().equals(query_user.getUser_password())){
                return LoginCheckEnum.PASSWORD_MISTAKE.getName();
            }
        }
        query_user.setUser_password(null);//设空防止泄露给其他用户
        session.setAttribute("user",query_user);
        return LoginCheckEnum.LOGIN_SUCCESS.getName();
    }

    //注销功能
    @RequestMapping("users/logout")
    @ResponseBody
    public void logout(HttpSession session){
        session.invalidate();
    }
}
