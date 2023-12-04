package com.restapi.restapidemo.Service;

import com.restapi.restapidemo.Dto.Product.*;

import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ReqCreateProduct reqProduct);

    List<ProductDto> searchProduct(String key);

    List<ProductDto> getAllProduct();

    ResProductAction deleteById(Integer id);


    ProductDto updateProduct(ReqUpdateProduct updatedProduct);
}
