package com.hacker.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "story")
public class Story {
	@Id
	private Integer id;
	@Column(name = "created_by")
	private String by;
	private Integer score;
	private Integer time;
	private String title;
	private String url;

	public Story(Integer id, String by, Integer score, Integer time, String title, String url) {
		
		this.id = id;
		this.by = by;
		this.score = score;
		this.time = time;
		this.title = title;
		this.url = url;
	}

	public Story() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Story [id=" + id + ", by=" + by + ", score=" + score + ", time=" + time + ", title=" + title + ", url="
				+ url + "]";
	}

}
