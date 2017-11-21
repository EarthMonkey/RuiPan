package service;

import model.GlobalConfigure;

import java.util.List;

/**
 * Created by ldchao on 2017/11/21.
 */
public interface GlobalConfigureService {

    public List<GlobalConfigure> getAllConfigures();

    public GlobalConfigure getConfigureByKey(String key);

    public GlobalConfigure addConfigure(GlobalConfigure globalConfigure);

    public GlobalConfigure updateConfigure(GlobalConfigure globalConfigure);

    public String deleteConfigure(Integer id);
}
