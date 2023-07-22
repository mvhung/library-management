package com.app.library.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ca_id", nullable = false)
    private int categoryId;
    @Column(name = "ca_name")
    private String categoryName;

    @Column(name = "ca_description")
    private String categoryDescription;



}
