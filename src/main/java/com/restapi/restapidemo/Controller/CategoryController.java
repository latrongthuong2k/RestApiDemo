package com.restapi.restapidemo.Controller;


import com.restapi.restapidemo.Dto.Category.CategoryDto;
import com.restapi.restapidemo.Dto.Category.ReqCreateCategory;
import com.restapi.restapidemo.Dto.Category.ResCategoryAction;
import com.restapi.restapidemo.Dto.Category.ResListCategory;
import com.restapi.restapidemo.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody ReqCreateCategory reqCreateCategory) {

        return ResponseEntity.ok(categoryService.saveCategory(reqCreateCategory));
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateCate(categoryDto));
    }

    @GetMapping("/get")
    public ResponseEntity<ResCategoryAction> getCategoryById(@RequestParam(name = "categoryId") Integer id) {
        return ResponseEntity.ok(categoryService.getCateById(id));
    }
    @GetMapping("/search")
    // search by name
    public ResponseEntity<List<ResCategoryAction>> searchCate(@RequestParam(name = "searchKey") String key) {
        return ResponseEntity.ok(categoryService.searchCate(key));
    }

    @GetMapping("/list-category")
    public ResponseEntity<List<ResListCategory>> getAllCategory() {
        List<ResListCategory> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ResCategoryAction> deleteCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(categoryService.deleteById(categoryId));
    }
}
