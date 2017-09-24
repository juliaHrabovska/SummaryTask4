package ua.nure.hrabovska.SummaryTask4.database.entity;

/**
 * Application entity
 *
 * @author Y. Hrabovska
 */
public class Application {
    private long enrollee_id;

    private long cathedra_id;

    private int status_id;

    public long getEnrollee_id() {
        return enrollee_id;
    }

    public void setEnrollee_id(long enrollee_id) {
        this.enrollee_id = enrollee_id;
    }

    public long getCathedra_id() {
        return cathedra_id;
    }

    public void setCathedra_id(long cathedra_id) {
        this.cathedra_id = cathedra_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "Account [" + "enrollee_id=" + enrollee_id
                + ", cathedra_id=" + cathedra_id
                + ", status_id=" + status_id
                + "]";
    }
}
