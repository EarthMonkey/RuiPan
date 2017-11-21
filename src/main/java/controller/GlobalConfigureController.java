package controller;

import model.GlobalConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GlobalConfigureService;
import util.SystemLog;

import java.util.List;

/**
 * Created by ldchao on 2017/11/21.
 */
@RestController
@RequestMapping(value = "/Configure")
public class GlobalConfigureController {

    @Autowired
    GlobalConfigureService globalConfigureService;

    //根据key获取配置数据
    @GetMapping(value = "get")
    public String getByKey(String key){
        GlobalConfigure globalConfigure=globalConfigureService.getConfigureByKey(key);
        return globalConfigure.getValue();
    }

    //获取所有配置项
    @GetMapping(value = "getAll")
    @SystemLog(module = "全局配置", methods = "获取配置")
    public List<GlobalConfigure> getAll(){
        return globalConfigureService.getAllConfigures();
    }

    //增加一条配置
    @PostMapping(value = "add")
    @SystemLog(module = "全局配置", methods = "增加配置")
    public GlobalConfigure add(String key,String value){
        return globalConfigureService.addConfigure(key,value);
    }

    //更新一条配置数据
    @PutMapping(value = "update")
    @SystemLog(module = "全局配置", methods = "更新配置")
    public GlobalConfigure update(Integer id,String value){
        return globalConfigureService.updateConfigure(id,value);
    }

    //删除一条配置数据
    @DeleteMapping(value = "delete")
    @SystemLog(module = "全局配置", methods = "删除配置")
    public String delete(Integer id){
        return globalConfigureService.deleteConfigure(id);
    }

}
