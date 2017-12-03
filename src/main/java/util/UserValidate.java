package util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

/**
 * Created by ldchao on 2017/11/22.
 */
public class UserValidate {

    private static String salt = "5225bbd835b3a1b80c1c98d54a0f7e4a";
    private static Log logger = LogFactory.getLog(UserValidate.class);

    public static String getLicense() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        try {
            return uuid + PasswordEncryption.getEncryptedPassword(uuid, salt);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        } catch (InvalidKeySpecException e) {
            logger.error(e);
        }
        return "";
    }

    public static boolean validate(String license) {

        if (license != null) {
            String licenses[] = license.split("-");
            if (licenses.length == 2) {
                try {
                    return PasswordEncryption.authenticate(licenses[0], licenses[1], salt);
                } catch (NoSuchAlgorithmException e) {
                    logger.error(e);
                } catch (InvalidKeySpecException e) {
                    logger.error(e);
                }
            }
        }
        return false;
    }
}
