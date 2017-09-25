package ua.nure.hrabovska.SummaryTask4.database.entity;

/**
 * Department entity
 *
 * @author Y. Hrabovska
 */
public class Department extends Entity {
    private String name;

    private long university_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(long university_id) {
        this.university_id = university_id;
    }

    @Override
    public String toString() {
        return "University [id=" + getId()
                + ", name=" + name
                + ", university_id=" + university_id
                + "]";
    }
}
