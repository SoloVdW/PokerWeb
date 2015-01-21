package models;

import javax.persistence.*;

/**
 * Created by Charl on 2015-01-20.
 */
@Entity
public class BaseEntityLongId {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long Id;

    public BaseEntityLongId() {
    }

    public BaseEntityLongId(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
