package com.management.system.inventory.model;

import com.management.system.model.BasicEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends BasicEntity {
    private String productType;
    private String productName;
    private Integer totalCapacity;
    private Integer remainingCapacity;

    @Override
    protected void onCreate() {
        super.onCreate();
        remainingCapacity = totalCapacity;
    }
}
