package service;

import vo.UserVO;


/**
 * Created by ldchao on 2017/10/15.
 */
public interface UserService {

    //Login
    public UserVO login(String username, String password, String ip);

}
