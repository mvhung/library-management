package com.app.library.model;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "au_id", nullable = false)
    private Long authorID;

    @Column(name = "au_fullname", nullable = false)
    private String authorFullName;

    @Column(name = "au_introduce")
    private String authorIntroduce;

    @Column(name = "au_image")
    private String authorImageUrl;

}