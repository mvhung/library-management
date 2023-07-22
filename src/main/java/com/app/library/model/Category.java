package com.app.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "ca_id", nullable = false, insertable = false)
    private int categoryID;

    @Column(name = "ca_name")
    private String categoryName;

    @Column(name = "ca_description")
    private String categoryDescription;

}
