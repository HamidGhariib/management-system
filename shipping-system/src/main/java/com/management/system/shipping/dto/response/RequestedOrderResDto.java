package com.management.system.shipping.dto.response;

import com.management.system.shipping.dto.OrderDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RequestedOrderResDto implements Serializable {
    private List<OrderDto> orderIdList;
}