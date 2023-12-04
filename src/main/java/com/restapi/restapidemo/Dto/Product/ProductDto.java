package com.restapi.restapidemo.Dto.Product;

import java.io.Serializable;
import java.util.Date;

public record ProductDto(
        String id,
        String name,
        Float price,
        Integer categoryId,
        String categoryName,
        Boolean status,
        Date created,
        Integer quantity
) implements Serializable {
}