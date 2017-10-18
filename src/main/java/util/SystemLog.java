package util;


import java.lang.annotation.*;

/**
 * Created by ldchao on 2017/10/18.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
        String module()  default "";
        String methods()  default "";
}
