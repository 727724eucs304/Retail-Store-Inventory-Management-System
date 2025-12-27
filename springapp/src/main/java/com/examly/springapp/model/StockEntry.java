package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class StockEntry {

    @Id
    private Long stockEntryId;
    private LocalDate entryDate;
    private Integer quantity;
    private String product;

    public StockEntry() {
    }

    public Long getStockEntryId() {
        return stockEntryId;
    }

    public void setStockEntryId(Long stockEntryId) {
        this.stockEntryId = stockEntryId;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
