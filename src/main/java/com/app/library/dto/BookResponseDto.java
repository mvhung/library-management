package com.app.library.dto;

import com.app.library.model.Author;
import com.app.library.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BookResponseDto  implements Serializable {
    private int bookId;
    private String bookTitle;
    private String bookDescription;
    private int bookPublishedYear;
    private int bookQuantity;
    private String categoryName;
    private String publisherName;
    private List<String> authorFullName;

    public BookResponseDto() {

    }


}
