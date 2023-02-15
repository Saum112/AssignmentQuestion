package com.hacker.project.model;

public class CommentDetails {
	private String text;
	private String user;

	public CommentDetails(String text, String user) {

		this.text = text;
		this.user = user;
	}

	public CommentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}