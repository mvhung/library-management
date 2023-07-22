package com.app.library.model;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Book}
 */
@Value
public class BookDto implements Serializable {
    String bookTitle;
    int bookPublishedYear;
    int bookQuantity;
    String bookDescription;
    String bookImageLink;
}