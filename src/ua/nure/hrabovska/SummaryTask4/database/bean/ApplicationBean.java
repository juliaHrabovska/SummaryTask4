package ua.nure.hrabovska.SummaryTask4.database.bean;

import java.io.Serializable;
import java.util.Map;
/**
 * Provide records for virtual table:
 * <pre>
 * |enrollee.id|enrollee.first_name|enrollee.last_name|enrollee.patronymic|application.status|
 *      |application.competitive_score|enrollee.examResults|
 * </pre>
 *
 * @author Y. Hrabovska
 */
public class ApplicationBean implements Serializable {

    private long enrollee_id;

    private String enrollee_first_name;

    private String enrollee_last_name;

    private String enrollee_patronymic;

    private String status;

    private int competitive_score;

    /**
     * Map contains exam name and it's result
     */
    private Map<String, Integer> exam_results;

    public String getEnrollee_first_name() {
        return enrollee_first_name;
    }

    public void setEnrollee_first_name(String enrollee_first_name) {
        this.enrollee_first_name = enrollee_first_name;
    }

    public String getEnrollee_last_name() {
        return enrollee_last_name;
    }

    public void setEnrollee_last_name(String enrollee_last_name) {
        this.enrollee_last_name = enrollee_last_name;
    }

    public String getEnrollee_patronymic() {
        return enrollee_patronymic;
    }

    public void setEnrollee_patronymic(String enrollee_patronymic) {
        this.enrollee_patronymic = enrollee_patronymic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCompetitive_score() {
        return competitive_score;
    }

    public void setCompetitive_score(int competitive_score) {
        this.competitive_score = competitive_score;
    }

    public Map<String, Integer> getExam_results() {
        return exam_results;
    }

    public void setExam_results(Map<String, Integer> exam_results) {
        this.exam_results = exam_results;
    }

    public long getEnrollee_id() {
        return enrollee_id;
    }

    public void setEnrollee_id(long enrollee_id) {
        this.enrollee_id = enrollee_id;
    }

    @Override
    public String toString() {
        return "ApplicationBean [enrolle_id=" + enrollee_id
                + ", enrollee_first_name=" + enrollee_first_name
                + ", enrollee_last_name=" + enrollee_last_name
                + ", enrollee_patronymic=" + enrollee_patronymic
                + ", status=" + status
                + ", competitive_score=" + competitive_score
                + ", exam_results=" + exam_results
                + "]";
    }
}
