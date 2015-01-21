package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Charl on 2015-01-20.
 */
@MappedSuperclass
public class BaseEntityLongId {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public BaseEntityLongId() {
    }

    public BaseEntityLongId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
