package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    @GetMapping
    public String getAllStockMovements() {
        return "All Stock Movements";
    }
}

