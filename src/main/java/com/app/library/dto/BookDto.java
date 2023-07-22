package com.app.library.dto;

import com.app.library.model.Book;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Book}
 */
@Data
@AllArgsConstructor
public class BookDto implements Serializable {
    String bookTitle;
    int bookPublishedYear;
    int bookQuantity;
    String bookDescription;
    String bookImageLink;
}