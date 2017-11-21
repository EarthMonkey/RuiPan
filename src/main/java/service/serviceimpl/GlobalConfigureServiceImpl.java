package service.serviceimpl;

import dao.GlobalConfigureDao;
import model.GlobalConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.GlobalConfigureService;

import java.util.List;

/**
 * Created by ldchao on 2017/11/21.
 */
@Service
public class GlobalConfigureServiceImpl implements GlobalConfigureService{

    @Autowired
    GlobalConfigureDao globalConfigureDao;

    @Override
    public List<GlobalConfigure> getAllConfigures() {
        return globalConfigureDao.findAll();
    }

    @Override
    public GlobalConfigure getConfigureByKey(String key) {
        return null;
    }

    @Override
    public GlobalConfigure addConfigure(GlobalConfigure globalConfigure) {
        return null;
    }

    @Override
    public GlobalConfigure updateConfigure(GlobalConfigure globalConfigure) {
        return null;
    }

    @Override
    public String deleteConfigure(Integer id) {
        return null;
    }
}
