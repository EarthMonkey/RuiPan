package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import util.GetClientMessageUtils;
import util.SystemLog;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ldchao on 2017/10/15.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    @SystemLog(module = "用户管理" ,methods = "用户登录")
    public String login(String username, String password, HttpServletRequest request) {

        UserVO user=userService.login(username,password, GetClientMessageUtils.getIpAddr(request));
        if(user.getLoginMessage().equalsIgnoreCase("success")) {
            request.getSession(false).invalidate();
            request.getSession(true);
            request.getSession().setAttribute("User", user);
        }
        return user.getLoginMessage();
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("User");
        request.getSession(false).invalidate();
        return "login";
    }

    //获取所有用户列表

    //修改当前用户密码

    //增加用户（只有admin用户有权限）

    //删除用户（只有admin用户有权限）
}
