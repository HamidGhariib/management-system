package com.management.system.order.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResDto {
    private String orderId;
    private String message;
}
