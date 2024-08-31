package com.management.system.order.dto.request;

import com.management.system.enumeration.OrderType;
import lombok.Data;

@Data
public class OrderReqDto  {
    private String productId;
    private Integer requestCount;
    private OrderType orderType;
}