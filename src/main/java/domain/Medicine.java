package domain;

public class Medicine {

    private String name;
    private String recommendedDosage;
    private String sideEffects;

    public Medicine(String name, String recommendedDosage, String sideEffects) {
        this.name = name;
        this.recommendedDosage = recommendedDosage;
        this.sideEffects = sideEffects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecommendedDosage() {
        return recommendedDosage;
    }

    public void setRecommendedDosage(String recommendedDosage) {
        this.recommendedDosage = recommendedDosage;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }
}
