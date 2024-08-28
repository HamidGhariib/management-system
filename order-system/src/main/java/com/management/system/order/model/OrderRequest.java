package com.management.system.order.model;

import com.management.system.model.BasicEntity;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest extends BasicEntity {
    private String orderType;
    private String requesterId;
    private String inventoryId ;
    private String requestCount ;
}