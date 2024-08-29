package com.management.system.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@MappedSuperclass
public class BasicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String statusId;
    private Date version;
    private Date creationDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicEntity that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BasicEntity {" +
                "id = " + id +
                "}";
    }

    @PrePersist
    protected void onCreate() {
        version = new Date();
        creationDate = new Date();
        statusId = "CREATED";
    }

    @PreUpdate
    protected void onUpdate() {
        version = new Date();
    }
}
