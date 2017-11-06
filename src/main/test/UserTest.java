import controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import service.UserService;
import vo.UserVO;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ldchao on 2017/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class UserTest {

    @Autowired
    UserService userService;
    @Autowired
    UserController userController;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void loginController(){
        String result = userController.login("test","test",request);
        System.out.println(result);
    }

    @Test
    public void addUser(){
        System.out.println(userService.addUser("ldchao","123"));
    }

    @Test
    public void login(){
//        UserVO userVO=userService.login("test","test","202.119.45.215");
        UserVO userVO=userService.login("ldchao","123","202.119.45.215");
        if(userVO.getLoginMessage().equalsIgnoreCase("no_user")){
            System.out.println("没有该用户");
        }
        else if(userVO.getLoginMessage().equalsIgnoreCase("success")) {
            System.out.println("用户名:" + userVO.getUsername());
            System.out.println("上次登录时间:" + userVO.getLastLoginTime());
            System.out.println("上次登录地:" + userVO.getLastLoginAddress());
        }else if(userVO.getLoginMessage().equalsIgnoreCase("wrong_password")){
            System.out.println("密码错误");
        }
    }

    @Test
    public void changePassword(){
        System.out.println(userService.changePassword("ldchao","1","123"));
        System.out.println(userService.changePassword("ldchao","123","123"));
    }

    @Test
    public void getAllUser(){
        List<UserVO> userVOS=userService.getAllUser();
        for (UserVO u:userVOS) {
            System.out.println(u.getUsername()+" "+u.getLastLoginTime()+" "+u.getLastLoginAddress());
        }
    }

    @Test
    public void deleteUser(){
        System.out.println(userService.deleteUser("ldchao2"));
        System.out.println(userService.deleteUser("ldchao"));
    }
}
