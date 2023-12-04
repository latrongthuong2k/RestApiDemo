package com.restapi.restapidemo.Dto.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record ReqCreateProduct(
        @NotNull(message = "ID must not be null")
        @Size(max = 5, message = "ID must not exceed 5 characters")
        String id,

        @NotNull(message = "Product name must not be null")
        @Size(max = 100, message = "Product name must not exceed 100 characters")
        String productName,

        @NotNull(message = "Price must not be null")
        Float price,

        @NotNull(message = "Category ID must not be null")
        Integer categoryId


) implements Serializable {
}