package service;

import vo.UserVO;

import java.util.List;


/**
 * Created by ldchao on 2017/10/15.
 */
public interface UserService {

    public UserVO login(String username, String password, String ip);

    public String changePassword(String username,String oldPassword,String newPassword);

    public String addUser(String username,String password);

    public String deleteUser(String username);

    public List<UserVO> getAllUser();
}
