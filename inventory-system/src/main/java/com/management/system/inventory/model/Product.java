package com.management.system.inventory.model;

import com.management.system.enumeration.ProductType;
import com.management.system.model.BasicEntity;
import com.management.system.enumeration.ProductStatus;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BasicEntity {
    private ProductType productType;
    private String productName;
    private Integer totalCapacity;
    private Integer remainingCapacity;

    @Override
    protected void onCreate() {
        super.onCreate();
        remainingCapacity = totalCapacity;
    }
}
