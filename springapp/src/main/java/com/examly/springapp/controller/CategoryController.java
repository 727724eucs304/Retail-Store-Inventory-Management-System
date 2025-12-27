package com.examly.springapp.controller;

import com.examly.springapp.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private List<Category> categoryList = new ArrayList<>();
    private long idCounter = 1;

    // CREATE
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        category.setCategoryId(idCounter++);
        categoryList.add(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        if (categoryList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categoryList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        for (Category category : categoryList) {
            if (category.getCategoryId().equals(id)) {
                return ResponseEntity.ok(category);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @RequestBody Category category) {
        category.setCategoryId(id);
        return ResponseEntity.ok(category);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/page/{page}/{size}")
    public Page<Category> getCategoriesWithPagination(
            @PathVariable int page,
            @PathVariable int size) {

        Pageable pageable = PageRequest.of(page, size);

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), categoryList.size());

        List<Category> subList = new ArrayList<>();

        if (start <= end) {
            subList = categoryList.subList(start, end);
        }

        return new PageImpl<>(subList, pageable, categoryList.size());
    }
}
