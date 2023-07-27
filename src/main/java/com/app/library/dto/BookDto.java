package com.app.library.dto;

import com.app.library.model.Author;
import com.app.library.model.Book;
import com.app.library.model.Category;
import com.app.library.model.Publisher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    List<Author> authors;

    Category category;
    Publisher publisher;


}