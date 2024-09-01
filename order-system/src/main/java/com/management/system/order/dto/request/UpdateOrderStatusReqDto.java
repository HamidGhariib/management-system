package com.management.system.order.dto.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateOrderStatusReqDto {
    private String orderId;
    private String status;
}
