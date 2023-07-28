package com.app.library.model;

import jakarta.persistence.*;

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
    private Long authorId;

    @Column(name = "au_fullname", nullable = false)
    private String authorFullName;

    @Column(name = "au_introduce")
    private String authorIntroduce;

    @Column(name = "au_image")
    private String authorImageUrl;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

}