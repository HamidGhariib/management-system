package com.management.system.order.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class RequestedOrderResDto {
    private List<OrderDto> orderIdList;
}