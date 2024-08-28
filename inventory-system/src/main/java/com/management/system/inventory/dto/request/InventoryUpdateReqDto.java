package com.management.system.inventory.dto.request;

import lombok.Data;

@Data
public class InventoryUpdateReqDto {
    private String inventoryId;
    private Integer requestCount;
}
