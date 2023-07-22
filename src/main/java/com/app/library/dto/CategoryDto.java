package com.app.library.dto;

import com.app.library.model.Category;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Category}
 */
@Data
@AllArgsConstructor
public class CategoryDto implements Serializable {
    int categoryId;
    String categoryName;
    String categoryDescription;
}