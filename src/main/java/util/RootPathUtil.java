package util;

import dao.ComplexSituationDao;
import dao.daoImpl.ComplexSituationDaoImpl;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import vo.SuccessfulCaseVO;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/20.
 */
public class RootPathUtil {
    private static RootPathUtil ourInstance = null;
    private static String rootPath="";

    public static RootPathUtil getInstance() {
        if(ourInstance==null){
            synchronized (RootPathUtil.class){
                if(ourInstance==null) {
                    ourInstance = new RootPathUtil();
                }
            }
        }

        return ourInstance;
    }

    private RootPathUtil() {
        WebApplicationContext webApplicationContext = ContextLoader
                .getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext
                .getServletContext();
        // 得到文件绝对路径
        rootPath = servletContext.getRealPath("/");
    }

    public String getRootPath(){
        return rootPath;
    }
}
