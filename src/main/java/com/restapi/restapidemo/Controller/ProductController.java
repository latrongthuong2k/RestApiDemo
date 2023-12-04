package com.restapi.restapidemo.Controller;



import com.restapi.restapidemo.Dto.Product.*;
import com.restapi.restapidemo.Service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("create")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ReqCreateProduct reqCreateProduct) {

        return ResponseEntity.ok(productService.saveProduct(reqCreateProduct));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ReqUpdateProduct categoryDto) {
        return ResponseEntity.ok(productService.updateProduct(categoryDto));
    }


    @GetMapping("/search")
    // search by name
    public ResponseEntity<List<ProductDto>> searchCate(@RequestParam(name = "searchKey") String key) {
        return ResponseEntity.ok(productService.searchProduct(key));
    }

    @GetMapping("/list-product")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> categories = productService.getAllProduct();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ResProductAction> deleteProduct(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.deleteById(productId));
    }
}
