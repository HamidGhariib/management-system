package com.management.system.order.controller;

import com.management.system.order.dto.request.OrderReqDto;
import com.management.system.order.dto.request.UpdateOrderStatusReqDto;
import com.management.system.order.dto.response.NewOrderResDto;
import com.management.system.order.dto.response.RequestedOrderResDto;
import com.management.system.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path = "/api/order/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Create new order request",
            description = "You can add new request here")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @RequestMapping(path = "/create-new-requst", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('USER')")
    @ResponseBody
    public NewOrderResDto createNewProduct(@RequestBody OrderReqDto orderReqDto) {
        return orderService.persistOrderReq(orderReqDto);
    }

    @Operation(
            summary = "Load pending order request",
            description = "Load all REQUESTED record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @RequestMapping(path = "/load-pending-order", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public RequestedOrderResDto loadRequestedOrder() {
        return orderService.loadRequestedOrder();
    }

    @Operation(
            summary = "Update order request status",
            description = "Update order request status to SHIPPED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @RequestMapping(path = "/update-order-status", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public void updateOrderStatus(@RequestBody UpdateOrderStatusReqDto updateOrderStatusReqDto) {
        orderService.updateOrderStatus(updateOrderStatusReqDto);
    }
}
