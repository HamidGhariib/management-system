package com.management.system.inventory.controller;

import com.management.system.inventory.dto.request.NewProductReqDto;
import com.management.system.inventory.dto.response.NewProductResDto;
import com.management.system.inventory.service.InventoryService;
import com.management.system.inventory.dto.request.InventoryUpdateReqDto;
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
@RequestMapping(path = "/api/inventory/v1")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Operation(
            summary = "Create new Product",
            description = "You can add new product to Inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @RequestMapping(path = "/create-new-product",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public NewProductResDto createNewProduct(@RequestBody NewProductReqDto newProductReqDto){
        return inventoryService.createNewProduct(newProductReqDto);
    }

    @Operation(
            summary = "Update Inventory",
            description = "This service update product capacity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @RequestMapping(path = "/update",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('USER')")
    @ResponseBody
    public void updateInventory(@RequestBody InventoryUpdateReqDto inventoryUpdateReqDto){
        inventoryService.updateInventory(inventoryUpdateReqDto);
    }
}
