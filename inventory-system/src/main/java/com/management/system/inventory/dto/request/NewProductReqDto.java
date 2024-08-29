package com.management.system.inventory.dto.request;

import lombok.Data;

@Data
public class NewProductReqDto {
    private String productType;
    private String productName;
    private Integer totalCapacity;
}
