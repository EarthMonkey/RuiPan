package service.serviceimpl;

import dao.GlobalConfigureDao;
import model.GlobalConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.GlobalConfigureService;

import java.sql.Timestamp;
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
        return globalConfigureDao.getDistinctByKey(key);
    }

    @Override
    public GlobalConfigure addConfigure(String key,String value) {
        GlobalConfigure globalConfigure=new GlobalConfigure();
        globalConfigure.setKey(key);
        globalConfigure.setValue(value);
        globalConfigure.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return globalConfigureDao.saveAndFlush(globalConfigure);
    }

    @Override
    public GlobalConfigure updateConfigure(Integer id,String value) {
        GlobalConfigure globalConfigure=globalConfigureDao.findOne(id);
        globalConfigure.setValue(value);
        globalConfigure.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return globalConfigureDao.saveAndFlush(globalConfigure);
    }

    @Override
    public String deleteConfigure(Integer id) {
        if(globalConfigureDao.exists(id)){
            globalConfigureDao.delete(id);
            return "success";
        }
        return "not_exist";
    }
}
