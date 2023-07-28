package com.app.library.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.*;
@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "au_id", nullable = false)
    private int authorId;

    @Column(name = "au_fullname", nullable = false)
    private String authorFullName;

    @Column(name = "au_introduce")
    private String authorIntroduce;

    @Column(name = "au_image")
    private String authorImageUrl;

    @JsonIgnore
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    private List<Book> books;

    public void removeBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            book.setAuthors(null);
        }
    }
}