package com.restapi.restapidemo.Dto.Category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record CategoryDto(
        @NotNull(message = "ID cannot be null")
        Integer id,
        @NotNull(message = "Name cannot be null")
        @Size(max = 50, message = "Name cannot exceed 50 characters") String name,
        @Size(max = 200, message = "Descriptions cannot exceed 200 characters")
        String descriptions,
        @NotNull(message = "Priority cannot be null")
        Integer priority,
        @NotNull(message = "Status cannot be null")
        Boolean status
) implements Serializable {
}