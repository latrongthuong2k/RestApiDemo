package com.restapi.restapidemo.Dto.Category;

import java.io.Serializable;

public record ResCategoryAction(
        Integer id,
        String name,
        Integer priority,
        Boolean status

) implements Serializable {
}