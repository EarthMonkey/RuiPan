package service.serviceimpl;

import dao.UserDao;
import model.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;
import util.GetAddress;
import util.PasswordEncryption;
import vo.UserVO;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/10/15.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    private static Log logger = LogFactory.getLog(UserServiceImpl.class);

    public UserVO login(String username, String password, String ip) {
        User user=userDao.findOne(username);
        UserVO userVO=new UserVO();
        if(user==null){
            userVO.setLoginMessage("no_user");
        }else{
            String salt=user.getSalt();
            String encryptedPassword=user.getPassword();
            boolean result=false;
            try {
                result=PasswordEncryption.authenticate(password, encryptedPassword, salt);
            } catch (NoSuchAlgorithmException e) {
                logger.error(e);
            } catch (InvalidKeySpecException e) {
                logger.error(e);
            }

            if(result){
                //获取上次登录信息
                userVO.setUsername(username);
                userVO.setLastLoginTime(user.getLastLoginTime());
                String ipParameter="ip="+user.getLastLoginIp();
                String address="未知登陆地";
                try {
                    address= GetAddress.getAddresses(ipParameter, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
                userVO.setLastLoginAddress(address);
                userVO.setLoginMessage("success");

                //更新该账户登录信息
                user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
                user.setLastLoginIp(ip);
                userDao.saveAndFlush(user);
                logger.debug("用户："+username+"登录成功。");
            }else{
                userVO.setLoginMessage("wrong_password");
            }
        }
        return userVO;
    }
}
