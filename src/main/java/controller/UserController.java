package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import util.GetClientMessageUtils;
import util.SystemLog;
import util.UserValidate;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldchao on 2017/10/15.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/validate")
    public String validate(HttpServletRequest request) {
        if (request.getSession(false) == null) {
            return "false";
        }
        if (request.getSession(false).getAttribute("User") != null) {
            UserVO userVO = (UserVO) request.getSession().getAttribute("User");
            if (UserValidate.validate(userVO.getLicense())) {
                return "true";
            }

        }
        return "false";
    }

    @PostMapping(value = "/login")
    @SystemLog(module = "用户管理", methods = "用户登录")
    public String login(String username, String password, HttpServletRequest request) {

        UserVO user = userService.login(username, password, GetClientMessageUtils.getIpAddr(request));
        if (user.getLoginMessage().equalsIgnoreCase("success")) {
            if (request.getSession(false) != null)
                request.getSession(false).invalidate();
            request.getSession(true);
            request.getSession().setAttribute("User", user);
        }
        return user.getLoginMessage();
    }

    @GetMapping(value = "/logout")
    @SystemLog(module = "用户管理", methods = "用户登出")
    public String logout(HttpServletRequest request) {
        request.getSession(false).removeAttribute("User");
        request.getSession(false).invalidate();
        return "logout_success";
    }

    //修改当前用户密码
    @PutMapping(value = "/changePassword")
    @SystemLog(module = "用户管理", methods = "修改密码")
    public String changePassword(HttpServletRequest request, String oldPassword, String newPassword) {
        UserVO userVO = (UserVO) request.getSession(false).getAttribute("User");
        return userService.changePassword(userVO.getUsername(), oldPassword, newPassword);
    }

    //查看当前用户信息
    @GetMapping(value = "/getUserMessage")
    @SystemLog(module = "用户管理", methods = "查看当前用户")
    public UserVO getUserMessage(HttpServletRequest request) {
        UserVO userVO = (UserVO) request.getSession(false).getAttribute("User");
        return userVO;
    }

    //获取所有用户列表(只有admin用户有权限)
    @GetMapping(value = "/getAllUser")
    @SystemLog(module = "用户管理", methods = "查看用户")
    public List<UserVO> getAllUser(HttpServletRequest request) {
        UserVO userVO = (UserVO) request.getSession(false).getAttribute("User");
        if (userVO.getUsername().equals("admin")) {
            return userService.getAllUser();
        } else {
            return new ArrayList<UserVO>();
        }

    }

    //增加用户（只有admin用户有权限）
    @PostMapping(value = "/addUser")
    @SystemLog(module = "用户管理", methods = "添加用户")
    public String addUser(HttpServletRequest request, String username, String password) {
        UserVO userVO = (UserVO) request.getSession(false).getAttribute("User");
        if (userVO.getUsername().equals("admin")) {
            return userService.addUser(username, password);
        } else {
            return "no_permission";
        }
    }

    //删除用户（只有admin用户有权限）
    @DeleteMapping(value = "/deleteUser")
    @SystemLog(module = "用户管理", methods = "删除用户")
    public String deleteUser(HttpServletRequest request, String username) {
        UserVO userVO = (UserVO) request.getSession(false).getAttribute("User");
        if (userVO.getUsername().equals("admin")) {
            return userService.deleteUser(username);
        } else {
            return "no_permission";
        }
    }
}
