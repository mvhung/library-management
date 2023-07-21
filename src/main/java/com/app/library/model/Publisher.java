package com.app.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher {

	@Id
	@Column(name = "pu_id")
	private int pu_id;

	@Column(name = "pu_name", nullable = false, length = 45)
	private String pu_name;

	@Column(name = "pu_introduce", nullable = false)
	private String pu_introduce;

	@Column(name = "pu_website")
	private String pu_website_link;

	@Column(name = "pu_image")
	private String pu_image_link;



	public Publisher() {

	}

	public Publisher(int pu_id, String pu_name, String pu_introduce, String pu_website_link, String pu_image_link) {
		super();
		this.pu_id = pu_id;
		this.pu_name = pu_name;
		this.pu_introduce = pu_introduce;
		this.pu_website_link = pu_website_link;
		this.pu_image_link = pu_image_link;
	}

	public int getPu_id() {
		return pu_id;
	}

	public void setPu_id(int pu_id) {
		this.pu_id = pu_id;
	}

	public String getPu_name() {
		return pu_name;
	}

	public void setPu_name(String pu_name) {
		this.pu_name = pu_name;
	}

	public String getPu_introduce() {
		return pu_introduce;
	}

	public void setPu_introduce(String pu_introduce) {
		this.pu_introduce = pu_introduce;
	}

	public String getPu_website_link() {
		return pu_website_link;
	}

	public void setPu_website_link(String pu_website_link) {
		this.pu_website_link = pu_website_link;
	}

	public String getPu_image_link() {
		return pu_image_link;
	}

	public void setPu_image_link(String pu_image_link) {
		this.pu_image_link = pu_image_link;
	}

	@Override
	public String toString() {
		return "Publisher [pu_id=" + pu_id + ", pu_name=" + pu_name + ", pu_introduce=" + pu_introduce
				+ ", pu_website_link=" + pu_website_link + ", pu_image_link=" + pu_image_link + "]";
	}
}
