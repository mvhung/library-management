package com.app.library.model;

public class Publisher {
	private int pu_id;
	private String pu_name;
	private String pu_introduce;
	private String pu_website_link;
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
	
}
