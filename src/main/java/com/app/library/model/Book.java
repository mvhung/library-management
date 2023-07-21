package com.app.library.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "bo_id", nullable = false)
    private int bo_id;

    @Column(name = "bo_title", nullable = false)
    private String bo_title;

    @Column(name = "bo_publishedYear", nullable = false)
    private int bo_publishedYear;

    @Column(name = "bo_quantity", nullable = false)
    private int bo_quantity;

    @Column(name = "bo_description")
    private String bo_description;

    @Column(name = "bo_image_link")
    private String bo_image_link;

    @Column(name = "bo_created_date", nullable = false)
    private Date bo_created_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ca_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pu_id")
    private Publisher publisher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "bo_id", referencedColumnName = "bo_id"), inverseJoinColumns = @JoinColumn(name = "au_id", referencedColumnName = "au_id"))
    private List<Author> authors;

    public Book() {
    }

    public Book(String bo_title, int bo_publishedYear, int bo_quantity, String bo_description, String bo_image_link, Date bo_created_date) {
        this.bo_title = bo_title;
        this.bo_publishedYear = bo_publishedYear;
        this.bo_quantity = bo_quantity;
        this.bo_description = bo_description;
        this.bo_image_link = bo_image_link;
        this.bo_created_date = bo_created_date;
    }

    public int getBo_id() {
        return bo_id;
    }

    public void setBo_id(int bo_id) {
        this.bo_id = bo_id;
    }

    public String getBo_title() {
        return bo_title;
    }

    public void setBo_title(String bo_title) {
        this.bo_title = bo_title;
    }

    public int getBo_publishedYear() {
        return bo_publishedYear;
    }

    public void setBo_publishedYear(int bo_publishedYear) {
        this.bo_publishedYear = bo_publishedYear;
    }

    public int getBo_quantity() {
        return bo_quantity;
    }

    public void setBo_quantity(int bo_quantity) {
        this.bo_quantity = bo_quantity;
    }

    public String getBo_description() {
        return bo_description;
    }

    public void setBo_description(String bo_description) {
        this.bo_description = bo_description;
    }

    public String getBo_image_link() {
        return bo_image_link;
    }

    public void setBo_image_link(String bo_image_link) {
        this.bo_image_link = bo_image_link;
    }

    public Date getBo_created_date() {
        return bo_created_date;
    }

    public void setBo_created_date(Date bo_created_date) {
        this.bo_created_date = bo_created_date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category ca_id) {
        this.category = ca_id;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher pu_id) {
        this.publisher = pu_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bo_id=" + bo_id +
                ", bo_title='" + bo_title + '\'' +
                ", bo_publishedYear=" + bo_publishedYear +
                ", bo_quantity=" + bo_quantity +
                ", bo_description='" + bo_description + '\'' +
                ", bo_image_link='" + bo_image_link + '\'' +
                ", bo_created_date=" + bo_created_date +
                ", category=" + category +
                ", publisher=" + publisher +
                '}';
    }

}
