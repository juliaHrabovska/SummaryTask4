package ua.nure.hrabovska.SummaryTask4.database.bean;

import ua.nure.hrabovska.SummaryTask4.database.entity.Entity;

public class EnrolleeBean extends Entity {

    private String first_name;

    private String last_name;

    private String patronymic;

    private int certificate_score;

    private String level;

    private String certificate_path;

    private boolean is_banned;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getCertificate_score() {
        return certificate_score;
    }

    public void setCertificate_score(int certificate_score) {
        this.certificate_score = certificate_score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCertificate_path() {
        return certificate_path;
    }

    public void setCertificate_path(String certificate_path) {
        this.certificate_path = certificate_path;
    }

    public boolean is_banned() {
        return is_banned;
    }

    public void setIs_banned(boolean is_banned) {
        this.is_banned = is_banned;
    }

    @Override
    public String toString() {
        return "EnrolleeBean [enrolle_id=" + getId()
                + ", enrollee_first_name=" + first_name
                + ", enrollee_last_name=" + last_name
                + ", enrollee_patronymic=" + patronymic
                + ", enrollee_certificate_score=" + certificate_score
                + ", enrollee_certificate_path=" + certificate_path
                + ", enrollee_is_banned=" + is_banned
                + ", enrollee_level=" + level
                + "]";
    }
}
