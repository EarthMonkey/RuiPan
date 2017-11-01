package controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.SystemLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by ldchao on 2017/10/21.
 */
@Controller
public class UEditorController {

    @RequestMapping(value = "ueditorcontroller")
    @ResponseBody
    @SystemLog(module = "UEditor后台控制入口" ,methods = "UEditor操作")
    public String controller(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Type" , "text/html");
        String rootPath = request.getServletContext().getRealPath("/");
        String result=new ActionEnter( request, rootPath).exec();
        System.out.println(result);
        return result;
    }
}
