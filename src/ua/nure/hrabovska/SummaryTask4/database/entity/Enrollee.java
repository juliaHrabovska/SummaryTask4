package ua.nure.hrabovska.SummaryTask4.database.entity;

/**
 * Enrollee entity
 *
 * @author Y. Hrabovska
 */
public class Enrollee extends Entity {

    private String first_name;

    private String last_name;

    private String patronymic;

    private int certificate_score;

    private long account_id;

    private long level_id;

    private String certificate_path;

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

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public long getLevel_id() {
        return level_id;
    }

    public void setLevel_id(long level_id) {
        this.level_id = level_id;
    }

    public String getCertificate_path() {
        return certificate_path;
    }

    public void setCertificate_path(String certificate_path) {
        this.certificate_path = certificate_path;
    }

    @Override
    public String toString() {
        return "Enrollee [" + "id=" + getId()
                + ", firs_name=" + first_name
                + ", last_name=" + last_name
                + ", patronymic=" + patronymic
                + ", certificate_score=" + certificate_score
                + ", account_id=" + account_id
                + ", level_id=" + level_id
                + ", certificate_path=" + certificate_path
                + "]";
    }
}
