package com.management.system.inventory.service;


import com.management.system.inventory.dto.request.InventoryUpdateReqDto;
import com.management.system.inventory.dto.request.NewProductReqDto;
import com.management.system.inventory.dto.response.NewProductResDto;
import com.management.system.inventory.dto.response.OrderResDto;
import com.management.system.inventory.exception.ProductOutOfRangeException;
import com.management.system.inventory.model.Inventory;
import com.management.system.inventory.repository.InventoryRepo;
import com.management.system.service.UtilService;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class InventoryService {

    private final InventoryRepo inventoryRepo;
    private final Logger logger = Logger.getLogger(InventoryService.class.getName());

    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public NewProductResDto createNewProduct(NewProductReqDto newProductReqDto){
        var inventoryRes = inventoryRepo.save(UtilService.convertModel(newProductReqDto,Inventory.class));
        logger.info("New product "+newProductReqDto+" created by id "+inventoryRes.getId());
        return NewProductResDto.builder().inventoryId(inventoryRes.getId()).build();
    }
    public OrderResDto updateInventory(InventoryUpdateReqDto inventoryUpdateReqDto) {
        logger.info("Update inventory by id "+ inventoryUpdateReqDto);
        this.checkProductAvailability(inventoryUpdateReqDto.getInventoryId(),inventoryUpdateReqDto.getRequestCount());
        var orderResDto = new OrderResDto();
        var orderId = inventoryRepo.save(Inventory.builder().build()).getId();
        orderResDto.setOrderId(orderId);
        return orderResDto;
    }

    private void checkProductAvailability(String inventoryId,Integer requestCount){
       var inventoryRes = inventoryRepo.findById(inventoryId);
       if (inventoryRes.isPresent() && (requestCount > inventoryRes.get().getRemainingCapacity())){
           throw new ProductOutOfRangeException();
       }
    }
}
