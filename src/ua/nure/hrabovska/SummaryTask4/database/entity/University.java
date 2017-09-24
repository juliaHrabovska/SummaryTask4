package ua.nure.hrabovska.SummaryTask4.database.entity;

/**
 * University entity
 *
 * @author Y. Hrabovska
 */
public class University extends Entity {

    private String name;

    private long place_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPlace_id() {
        return place_id;
    }

    public void setPlace_id(long place_id) {
        this.place_id = place_id;
    }

    @Override
    public String toString() {
        return "University [id=" + getId()
                + ", name=" + name
                + ", place_id=" + place_id
                + "]";
    }
}
