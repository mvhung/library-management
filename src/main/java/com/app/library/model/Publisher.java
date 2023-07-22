package com.app.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "publishers")
public class Publisher {

	@Id
	@Column(name = "pu_id")
	private int publisherID;

	@Column(name = "pu_name", nullable = false, length = 45)
	private String publisherName;

	@Column(name = "pu_introduce", nullable = false)
	private String publisherIntroduce;

	@Column(name = "pu_website")
	private String publisherWebsiteUrl;

	@Column(name = "pu_image")
	private String publisherImageUrl;

	public Publisher() {

	}

	public Publisher(int publisherID, String publisherName, String publisherIntroduce, String publisherWebsiteUrl,
			String publisherImageUrl) {
		super();
		this.publisherID = publisherID;
		this.publisherName = publisherName;
		this.publisherIntroduce = publisherIntroduce;
		this.publisherWebsiteUrl = publisherWebsiteUrl;
		this.publisherImageUrl = publisherImageUrl;
	}

}
