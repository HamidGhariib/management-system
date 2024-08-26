package com.management.system.orderrequest.service;


import com.management.system.orderrequest.dto.request.InventoryReqDto;
import com.management.system.orderrequest.dto.response.OrderResDto;
import com.management.system.orderrequest.model.Inventory;
import com.management.system.orderrequest.repository.InventoryRepo;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class InventoryService {

    private final InventoryRepo inventoryRepo;
    private final Logger logger = Logger.getLogger(InventoryService.class.getName());

    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public OrderResDto updateInventory(InventoryReqDto inventoryReqDto) {
        logger.info("Update inventory by id "+inventoryReqDto);
        OrderResDto orderResDto = new OrderResDto();
        String orderId = inventoryRepo.save(Inventory.builder().build()).getId().toString();
        orderResDto.setOrderId(orderId);
        return orderResDto;
    }
}
