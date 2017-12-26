package service.serviceimpl;

import dao.ComplexSituationDao;
import dao.GlobalConfigureDao;
import model.GlobalConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.GlobalConfigureService;
import vo.CompanyVO;
import vo.ContactInformationVO;
import vo.HomePageVO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/21.
 */
@Service
public class GlobalConfigureServiceImpl implements GlobalConfigureService{

    @Autowired
    GlobalConfigureDao globalConfigureDao;

    @Autowired
    ComplexSituationDao complexSituationDao;

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

    @Override
    public ContactInformationVO getContactInformation() {

        Map<String,GlobalConfigure> list=complexSituationDao.getGlobalConfigures("ci_");
        ContactInformationVO contactInformationVO=new ContactInformationVO();
        contactInformationVO.setAddress(list.get("ci_address"));
        contactInformationVO.setPhoneNumber(list.get("ci_phoneNumber"));
        contactInformationVO.setEmail(list.get("ci_email"));
        contactInformationVO.setQq(list.get("ci_qq"));
        contactInformationVO.setwChart(list.get("ci_wChart"));
        contactInformationVO.setQRcode(list.get("ci_QRcode"));
        return contactInformationVO;
    }

    @Override
    public CompanyVO getCompanyInformation() {
        Map<String,GlobalConfigure> list=complexSituationDao.getGlobalConfigures("c_");
        CompanyVO companyVO=new CompanyVO();
        companyVO.setIntroduce(list.get("c_introduce"));
        companyVO.setCharacteristic1(list.get("c_characteristic1"));
        companyVO.setCharacteristic2(list.get("c_characteristic2"));
        companyVO.setCharacteristic3(list.get("c_characteristic3"));
        return companyVO;
    }

    @Override
    public HomePageVO getHomepageInformation() {
        Map<String,GlobalConfigure> list=complexSituationDao.getGlobalConfigures("h_");
        HomePageVO homePageVO=new HomePageVO();
        homePageVO.setCharacteristic1(list.get("h_characteristic1"));
        homePageVO.setCharacteristic2(list.get("h_characteristic2"));
        homePageVO.setCharacteristic3(list.get("h_characteristic3"));
        homePageVO.setConsultant1(list.get("h_consultant1"));
        homePageVO.setConsultant2(list.get("h_consultant2"));
        homePageVO.setConsultant3(list.get("h_consultant3"));
        homePageVO.setConsultant4(list.get("h_consultant4"));
        return homePageVO;
    }
}
