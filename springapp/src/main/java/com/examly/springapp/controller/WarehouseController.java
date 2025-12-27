package com.examly.springapp.controller;

import com.examly.springapp.model.Warehouse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private List<Warehouse> warehouseList = new ArrayList<>();
    private long warehouseIdCounter = 1;

    // POST /api/warehouses  (Day10)
    @PostMapping
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody Warehouse warehouse) {
        warehouse.setWarehouseId(warehouseIdCounter++);
        warehouseList.add(warehouse);
        return new ResponseEntity<>(warehouse, HttpStatus.CREATED);
    }

    // GET /api/warehouses  (Day10)
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseList);
    }

    // GET /api/warehouses/{id}  (Day10)
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        for (Warehouse warehouse : warehouseList) {
            if (warehouse.getWarehouseId().equals(id)) {
                return ResponseEntity.ok(warehouse);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // PUT /api/warehouses/{id}  (Day10)
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(
            @PathVariable Long id,
            @RequestBody Warehouse updatedWarehouse) {

        for (Warehouse warehouse : warehouseList) {
            if (warehouse.getWarehouseId().equals(id)) {
                warehouse.setName(updatedWarehouse.getName());
                warehouse.setLocation(updatedWarehouse.getLocation());
                return ResponseEntity.ok(warehouse);
            }
        }
        return ResponseEntity.notFound().build();
    }

   


    // GET /api/warehouses/location/{location}  (Day11 + Day12)
    @GetMapping("/location/{location}")
    public ResponseEntity<?> getWarehousesByLocation(@PathVariable String location) {

        List<Warehouse> result = new ArrayList<>();

        for (Warehouse warehouse : warehouseList) {
            if (warehouse.getLocation() != null &&
                warehouse.getLocation().equalsIgnoreCase(location)) {
                result.add(warehouse);
            }
        }

        // REQUIRED FOR DAY12 - Order 74
        if (result.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("No warehouses found at location: " + location);
        }

        return ResponseEntity.ok(result);
    }

    // GET /api/warehouses/location/{location}/name/{name}  (Day12 - Order 73)
    @GetMapping("/location/{location}/name/{name}")
    public ResponseEntity<List<Warehouse>> getWarehousesByLocationAndName(
            @PathVariable String location,
            @PathVariable String name) {

        List<Warehouse> result = new ArrayList<>();

        for (Warehouse warehouse : warehouseList) {
            if (warehouse.getLocation() != null &&
                warehouse.getName() != null &&
                warehouse.getLocation().equalsIgnoreCase(location) &&
                warehouse.getName().equalsIgnoreCase(name)) {
                result.add(warehouse);
            }
        }

        return ResponseEntity.ok(result);
    }
}

