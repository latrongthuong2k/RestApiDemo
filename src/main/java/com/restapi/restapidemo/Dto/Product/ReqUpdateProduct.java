package com.restapi.restapidemo.Dto.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record ReqUpdateProduct(
        @NotNull(message = "ID cannot be null")
        @Size(max = 5, message = "ID cannot exceed 5 characters")
        String id,
        @NotNull(message = "Product name cannot be null")
        @Size(max = 100, message = "Product name cannot exceed 100 characters")
        String productName,
        @NotNull(message = "Price cannot be null")
        Float price,
        @NotNull(message = "Category ID cannot be null")
        Integer categoryId,
        @NotNull(message = "Status cannot be null")
        Boolean status
) implements Serializable {
}