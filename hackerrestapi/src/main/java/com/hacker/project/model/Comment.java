package com.hacker.project.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Comment {
	@Id
	private long id;
	@Column(name = "created_by")
	private String by;
	private long time;
	private String text;
	private int descendants;
	private List<Long> kids;

	public Comment(long id, String by, long time, String text, int descendants, List<Long> kids) {

		this.id = id;
		this.by = by;
		this.time = time;
		this.text = text;
		this.descendants = descendants;
		this.kids = kids;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getDescendants() {
		return descendants;
	}

	public void setDescendants(int descendants) {
		this.descendants = descendants;
	}

	public List<Long> getKids() {
		return kids;
	}

	public void setKids(List<Long> kids) {
		this.kids = kids;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", by=" + by + ", time=" + time + ", text=" + text + ", descendants=" + descendants
				+ ", kids=" + kids + "]";
	}

}
