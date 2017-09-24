package ua.nure.hrabovska.SummaryTask4.database.entity;

/**
 * Exam entity
 *
 * @author Y. Hrabovska
 */
public class Exam extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Exam: [id=" + getId()
                + ", name=" + name + "]";
    }
}
