package com.management.system.order.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewOrderResDto {
    private String orderId;
    private String message;
}
