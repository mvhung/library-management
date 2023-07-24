package com.app.library.dto;

import com.app.library.model.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Book}
 */
@Data
@AllArgsConstructor
public class BookDto implements Serializable {
    int bookId;
    String bookTitle;
    int bookPublishedYear;
    int bookQuantity;
    String bookDescription;
    String bookImageLink;
}