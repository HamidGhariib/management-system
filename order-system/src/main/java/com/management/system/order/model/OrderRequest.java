package com.management.system.order.model;

import com.management.system.enumeration.OrderType;
import com.management.system.model.BasicEntity;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest extends BasicEntity {
    private OrderType orderType;
    private String requesterUsername;
    private String productId ;
    private Integer requestCount ;
}