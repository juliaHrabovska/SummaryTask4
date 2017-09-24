package ua.nure.hrabovska.SummaryTask4.database.entity;

/**
 * Place entity
 *
 * @author Y. Hrabovska
 */
public class Place extends Entity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Place [id=" + getId() +
                ", name=" + name
                + "]";
    }
}
