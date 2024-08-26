package com.management.system.inventory.model;

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
}