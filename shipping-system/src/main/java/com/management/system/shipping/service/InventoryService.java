package com.management.system.shipping.service;


import com.management.system.shipping.dto.request.InventoryReqDto;
import com.management.system.shipping.dto.response.OrderResDto;
import com.management.system.shipping.model.Inventory;
import com.management.system.shipping.repository.InventoryRepo;
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
