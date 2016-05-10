package dictionary;

public enum NAType {
    SKETCH("Эскиз"), BENDING("Гибка"), ASSEMBLY("Сборка"), CUT_PIPE("Резка труб"), HARVESTING("Заготовительная"), LASER_CUT("Лазерная резка");

    private String description;

    NAType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
