package com.management.system.inventory.service;


import com.management.system.inventory.dto.request.InventoryUpdateReqDto;
import com.management.system.inventory.dto.request.NewProductReqDto;
import com.management.system.inventory.dto.response.NewProductResDto;
import com.management.system.inventory.exception.ProductOutOfRangeException;
import com.management.system.inventory.model.Inventory;
import com.management.system.inventory.repository.InventoryRepo;
import com.management.system.service.UtilService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class InventoryService {

    private final InventoryRepo inventoryRepo;
    private final Logger logger = Logger.getLogger(InventoryService.class.getName());

    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public NewProductResDto createNewProduct(NewProductReqDto newProductReqDto) {
        var inventoryRes = inventoryRepo.save(UtilService.convertModel(newProductReqDto, Inventory.class));
        logger.info("New product " + newProductReqDto + " created by id " + inventoryRes.getId());
        return NewProductResDto.builder().inventoryId(inventoryRes.getId()).build();
    }

    public void updateInventory(InventoryUpdateReqDto inventoryUpdateReqDto) {

        logger.info("Update inventory by id " + inventoryUpdateReqDto.getInventoryId() + " and request count " + inventoryUpdateReqDto.getRequestCount());
        var inventoryRes = inventoryRepo.findById(Long.parseLong(inventoryUpdateReqDto.getInventoryId()));

        this.checkProductAvailability(inventoryUpdateReqDto.getRequestCount(), inventoryRes);

        inventoryRepo.updateById(Long.parseLong(inventoryUpdateReqDto.getInventoryId())
                , inventoryRes.get().getRemainingCapacity() - inventoryUpdateReqDto.getRequestCount());
    }

    private void checkProductAvailability(Integer requestCount, Optional<Inventory> inventoryRes) {
        if (inventoryRes.isPresent() && (requestCount > inventoryRes.get().getRemainingCapacity())) {
            throw new ProductOutOfRangeException();
        }
    }
}
