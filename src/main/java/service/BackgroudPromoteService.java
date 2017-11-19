package service;

import model.BackgroundPromote;

import java.util.List;

public interface BackgroudPromoteService {

    public List<BackgroundPromote> getBackgroundPromoteByCategory(String category,Integer flag);

    public BackgroundPromote getBackgroundPromote(Integer id);

    public BackgroundPromote addBackgroundPromote(BackgroundPromote backgroundPromote);

    public BackgroundPromote updateBackgroundPromote(BackgroundPromote backgroundPromote);

    public String deleteBackgroundPromote(Integer id);

}
