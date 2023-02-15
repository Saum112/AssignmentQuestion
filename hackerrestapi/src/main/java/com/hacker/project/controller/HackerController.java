package com.hacker.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.project.exception.CommentNotFoundException;
import com.hacker.project.model.Comment;
import com.hacker.project.model.CommentDetails;
import com.hacker.project.model.Story;
import com.hacker.project.service.HackerNewsService;

@RestController
public class HackerController {

	@Autowired
	private HackerNewsService hackerNewsService;

	@GetMapping("/top-stories")
	public ResponseEntity<List<Story>> getTopStories() {
		try {
			List<Story> stories = hackerNewsService.getTopStories();
			return ResponseEntity.ok(stories);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/past-stories")
	public ResponseEntity<List<Story>> getPastStories() {
		try {
			List<Story> stories = hackerNewsService.getPastStories();
			return ResponseEntity.ok(stories);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/comments")
	public ResponseEntity<List<CommentDetails>> getComments(@RequestParam int storyId) {

		try {
			List<CommentDetails> comments = hackerNewsService.getComments(storyId);
			if (comments.isEmpty()) {
				throw new CommentNotFoundException("Comment with ID " + storyId + " not found");
			}
			return ResponseEntity.ok(comments);
		}

		catch (CommentNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
