package com.restapi.restapidemo.Dto.Category;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record ReqCreateCategory(
        @NotNull(message = "Name cannot be null")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String name,
        @NotNull(message = "Description cannot be null")
        @Lob
        String descriptions,
        @NotNull(message = "Priority cannot be null")
        Integer priority

) implements Serializable {
}