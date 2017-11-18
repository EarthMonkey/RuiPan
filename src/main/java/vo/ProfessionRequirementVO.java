package vo;

public class ProfessionRequirementVO {

    private String profession;
    private String scoreRequirements;
    private String applicationDifficulty;

    public ProfessionRequirementVO() {
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getScoreRequirements() {
        return scoreRequirements;
    }

    public void setScoreRequirements(String scoreRequirements) {
        this.scoreRequirements = scoreRequirements;
    }

    public String getApplicationDifficulty() {
        return applicationDifficulty;
    }

    public void setApplicationDifficulty(String applicationDifficulty) {
        this.applicationDifficulty = applicationDifficulty;
    }
}
