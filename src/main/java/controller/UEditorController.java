package controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import util.FileManager;
import util.SystemLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by ldchao on 2017/10/21.
 */
@RestController
public class UEditorController {

    @RequestMapping(value = "ueditorcontroller")
    @SystemLog(module = "UEditor后台控制" ,methods = "UEditor操作")
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

    @RequestMapping(value = "/uploadText")
    @SystemLog(module = "UEditor后台控制" ,methods = "UEditor正文提交")
    public String uploadText(HttpServletRequest request, String text){
        String uploadUrl = request.getSession().getServletContext().getRealPath("/")
                + "upload/text/";
        String fileName= UUID.randomUUID().toString().replaceAll("-", "")+".html";
        FileManager.WriteFile(text,uploadUrl,fileName,false);
        return fileName;
    }

    @RequestMapping(value = "/updateText")
    @SystemLog(module = "UEditor后台控制" ,methods = "修改UEditor正文内容")
    public String updateText(HttpServletRequest request, String text,String fileName){
        String uploadUrl = request.getSession().getServletContext().getRealPath("/")
                + "upload/text/";
        FileManager.WriteFile(text,uploadUrl,fileName,false);
        return fileName;
    }

    @RequestMapping(value = "/getText")
    @SystemLog(module = "UEditor后台控制" ,methods = "获取UEditor正文提交")
    public String getUEditorText(HttpServletRequest request, String path){
        String uploadUrl = request.getSession().getServletContext().getRealPath("/")
                + "upload/text/";
        return FileManager.ReadFile(uploadUrl+path);
    }

}
