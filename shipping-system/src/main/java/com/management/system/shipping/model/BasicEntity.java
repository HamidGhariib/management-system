package com.management.system.shipping.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;
import java.util.Objects;

@Data
@MappedSuperclass
public class BasicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;


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
}
