package com.app.library.model;

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

	@OneToMany(mappedBy = "publisher")
	private List<Book> books;
}
