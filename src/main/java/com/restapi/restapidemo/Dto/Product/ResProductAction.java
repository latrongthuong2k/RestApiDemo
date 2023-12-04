package com.restapi.restapidemo.Dto.Product;

import java.io.Serializable;

public record ResProductAction(
        String id,
        String name,
        Integer categoryId,
        String categoryName,
        Boolean status

) implements Serializable {
}