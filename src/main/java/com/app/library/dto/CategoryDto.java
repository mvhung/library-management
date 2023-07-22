package com.app.library.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.app.library.model.Category}
 */
@Value
public class CategoryDto implements Serializable {
    String categoryName;
    String categoryDescription;
}