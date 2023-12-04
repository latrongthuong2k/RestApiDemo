package com.restapi.restapidemo.Dto.Category;

import java.io.Serializable;

public record ResListCategory(
        Integer id,
        String name,
        Integer priority,
        Boolean status

) implements Serializable {
}