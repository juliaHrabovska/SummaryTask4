package ua.nure.hrabovska.SummaryTask4.database.entity;

import java.io.Serializable;

/**
 * Class describes an abstract Entity in database
 *
 * @author Y. Hrabovska
 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -49054550924103012L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

