package com.app.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "au_id", nullable = false)
    private Long au_id;

    @Column(name = "au_fullname", nullable = false)
    private String au_fullname;

    @Column(name = "au_introduce")
    private String au_introduce;

    @Column(name = "au_image")
    private String au_image;

}