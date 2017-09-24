package ua.nure.hrabovska.SummaryTask4.database.bean;

/**
 * Provide records for virtual table:
 * <pre>
 * |application.status|university.name|cathedra.type_of_training|department.name|cathedra.id|
 * </pre>
 *
 * @author Y. Hrabovska
 */
public class SubmittedAppBean {

    private String status;

    private String university;

    private String type_of_training;

    private String department;

    private String cathedra;

    private Long cathedra_id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getType_of_training() {
        return type_of_training;
    }

    public void setType_of_training(String type_of_training) {
        this.type_of_training = type_of_training;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCathedra() {
        return cathedra;
    }

    public void setCathedra(String cathedra) {
        this.cathedra = cathedra;
    }

    public Long getCathedra_id() {
        return cathedra_id;
    }

    public void setCathedra_id(Long cathedra_id) {
        this.cathedra_id = cathedra_id;
    }

    @Override
    public String toString() {
        return "SubmittedApp [status=" + status
                + ", university=" + university
                + ", type_of_training=" + type_of_training
                + ", department=" + department
                + ", cathedra=" + cathedra
                + ", cathedra_id=" + cathedra_id
                + "]";
    }
}
