package util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by ldchao on 2017/11/22.
 */
public class UserValidate {

    private static String salt="5225bbd835b3a1b80c1c98d54a0f7e4a";
    private static Log logger = LogFactory.getLog(UserValidate.class);

    public static String getLicense(String name){
        try {
            return PasswordEncryption.getEncryptedPassword(name,salt);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        } catch (InvalidKeySpecException e) {
            logger.error(e);
        }
        return "";
    }

    public static boolean validate(String name,String license){

        try {
            return  PasswordEncryption.authenticate(name,license,salt);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        } catch (InvalidKeySpecException e) {
            logger.error(e);
        }
        return false;
    }
}
