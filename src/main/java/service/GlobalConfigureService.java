package service;

import model.GlobalConfigure;

import java.util.List;

/**
 * Created by ldchao on 2017/11/21.
 */
public interface GlobalConfigureService {

    public List<GlobalConfigure> getAllConfigures();

    public GlobalConfigure getConfigureByKey(String key);

    public GlobalConfigure addConfigure(String key,String value);

    public GlobalConfigure updateConfigure(Integer id,String value);

    public String deleteConfigure(Integer id);
}
