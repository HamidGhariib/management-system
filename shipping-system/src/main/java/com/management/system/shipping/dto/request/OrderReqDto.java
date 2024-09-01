package com.management.system.shipping.dto.request;

import com.management.system.shipping.dto.OrderDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderReqDto  {
    private List<OrderDto> orderIdList;
}