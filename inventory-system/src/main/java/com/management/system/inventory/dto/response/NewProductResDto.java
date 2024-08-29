package com.management.system.inventory.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewProductResDto {
    private Long inventoryId;
}