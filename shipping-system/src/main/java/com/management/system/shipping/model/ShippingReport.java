package com.management.system.shipping.model;

import com.management.system.model.BasicEntity;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingReport extends BasicEntity {
    private String orderId;
}
