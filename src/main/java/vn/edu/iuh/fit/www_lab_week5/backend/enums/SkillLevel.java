package vn.edu.iuh.fit.www_lab_week5.backend.enums;
public enum SkillLevel {
    MASTER(5),
    PROFESSIONAL(4),
    ADVANCED(3),
    INTERMEDIATE(2),
    BEGINNER(1);

    private int value;

    SkillLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
