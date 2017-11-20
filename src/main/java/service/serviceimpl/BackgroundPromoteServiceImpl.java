package service.serviceimpl;

import dao.BackgroundPromoteDao;
import model.BackgroundPromote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BackgroudPromoteService;
import util.FileManager;

import java.util.List;

@Service
public class BackgroundPromoteServiceImpl implements BackgroudPromoteService{

    @Autowired
    BackgroundPromoteDao backgroundPromoteDao;

    @Override
    public List<BackgroundPromote> getBackgroundPromoteByCategory(String category, Integer flag) {
        return backgroundPromoteDao.findAllByCategoryAndFlagOrderByUpdateAtDesc(category,flag);
    }

    @Override
    public BackgroundPromote getBackgroundPromote(Integer id) {
        return backgroundPromoteDao.findOne(id);
    }

    @Override
    public BackgroundPromote addBackgroundPromote(BackgroundPromote backgroundPromote) {
        return backgroundPromoteDao.saveAndFlush(backgroundPromote);
    }

    @Override
    public BackgroundPromote updateBackgroundPromote(BackgroundPromote backgroundPromote) {
        return backgroundPromoteDao.saveAndFlush(backgroundPromote);
    }

    @Override
    public String deleteBackgroundPromote(Integer id) {
        if(backgroundPromoteDao.exists(id)){
            BackgroundPromote backgroundPromote=backgroundPromoteDao.findOne(id);
            backgroundPromoteDao.delete(id);
            FileManager.deleteImage(backgroundPromote.getThumbnail());
            FileManager.deleteText(backgroundPromote.getTextPath());
            return "success";
        }
        return "not_exist";
    }
}
