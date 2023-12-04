package com.restapi.restapidemo.Impl;

import com.restapi.restapidemo.Dto.Product.*;
import com.restapi.restapidemo.Entity.Category;
import com.restapi.restapidemo.Entity.Product;
import com.restapi.restapidemo.Exception.DuplicateResourceException;
import com.restapi.restapidemo.Exception.ResourceNotFoundException;
import com.restapi.restapidemo.Repository.ProductRepository;
import com.restapi.restapidemo.Service.CategoryService;
import com.restapi.restapidemo.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public ProductDto saveProduct(ReqCreateProduct reqProduct) {
        Optional<Product> checkCate = productRepository.findByName(reqProduct.productName());
        if (checkCate.isPresent()) {
            throw new DuplicateResourceException("Product is exist, please input another category name");
        }
        Category category = categoryService.findById(reqProduct.categoryId());
        Product product = Product.builder()
                .id(reqProduct.id())
                .price(reqProduct.price())
                .name(reqProduct.productName())
                .category(category)
                .status(true)
                .build();
        productRepository.save(product);
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getStatus(),
                product.getCreated(),
                product.getQuantity());
    }

    @Override
    public List<ProductDto> searchProduct(String key) {
        List<Product> products = productRepository.search(key);
        return products.stream().map(product -> new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getStatus(),
                product.getCreated(),
                product.getQuantity())).toList();
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return  products.stream().map(product -> new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getStatus(),
                product.getCreated(),
                product.getQuantity())).toList();
    }

    @Override
    public ResProductAction deleteById(Integer id) {
        Product product = foundProduct(id);
        ResProductAction res = new ResProductAction(product.getId(),
                product.getName(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getStatus());
        productRepository.deleteById(id);
        return res;
    }

//    @Override
//    public ResProductAction getCateById(Integer id) {
//        Product product = foundProduct(id);
//        return new Product(product.getId(),
//                product.getName(),
//                product.getPriority(),
//                product.getStatus());
//    }

    @Override
    public ProductDto updateProduct(ReqUpdateProduct updatedProduct) {
        Category category = categoryService.findById(updatedProduct.categoryId());
        Product product = Product.builder()
                .id(updatedProduct.id())
                .name(updatedProduct.productName())
                .status(updatedProduct.status())
                .status(updatedProduct.status())
                .category(category)
                .build();
        productRepository.save(product);
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getStatus(),
                product.getCreated(),
                product.getQuantity());
    }

    private Product foundProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product id" + "is not found"));
    }
}