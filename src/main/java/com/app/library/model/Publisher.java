package com.app.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pu_id")
	private int publisherId;

	@Column(name = "pu_name", nullable = false, length = 45)
	private String publisherName;

	@Column(name = "pu_introduce")
	private String publisherIntroduce;

	@Column(name = "pu_website")
	private String publisherWebsiteUrl;

	@Column(name = "pu_image")
	private String publisherImageUrl;

	@JsonIgnore
	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
	private List<Book> books;

	public void removeBook(Book book) {
		// Kiểm tra xem cuốn sách có tồn tại trong danh sách cuốn sách của nhà xuất bản hay không
		if (books.contains(book)) {
			books.remove(book);
			book.setPublisher(null); // set publisher thành null để gỡ bỏ liên kết
		}
	}
}
