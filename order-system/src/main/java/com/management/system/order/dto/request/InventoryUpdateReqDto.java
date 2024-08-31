package com.management.system.order.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryUpdateReqDto {
    private String productId;
    private Integer requestCount;
}
