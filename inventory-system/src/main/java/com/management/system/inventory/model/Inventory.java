package com.management.system.inventory.model;

import com.management.system.model.BasicEntity;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends BasicEntity {
    private String productType;
}
