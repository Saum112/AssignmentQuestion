package com.hacker.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.hacker.project.model.CommentDetails;
import com.hacker.project.model.Story;
import com.hacker.project.service.HackerNewsService;

@ExtendWith(MockitoExtension.class)
public class HackerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private HackerController hackerController;

	@Mock
	private HackerNewsService hackerNewsService;

	private List<Story> stories;

	@BeforeEach
	public void setUp() {
		stories = new ArrayList<>();
		stories.add(new Story(1, "awasa1h", 123, 123456, "title", "url"));
	}

	@Test
	void testGetTopStories_exception() {
		when(hackerNewsService.getTopStories()).thenThrow(new RuntimeException());
		ResponseEntity<List<Story>> response = hackerController.getTopStories();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	@Test
	public void getPastStories() {
		when(hackerNewsService.getPastStories()).thenReturn(stories);
		ResponseEntity<List<Story>> response = hackerController.getPastStories();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(stories, response.getBody());
	}

	@Test
	void testGetPastStories() {

		when(hackerNewsService.getPastStories()).thenReturn(stories);
		ResponseEntity<List<Story>> response = hackerController.getPastStories();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(stories == response.getBody());
	}

	@Test
	void testGetPastStories_exception() {
		when(hackerNewsService.getPastStories()).thenThrow(new RuntimeException());
		ResponseEntity<List<Story>> response = hackerController.getPastStories();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	@Test
	void testGetComments_InternalServerError() {
		when(hackerNewsService.getComments(1)).thenThrow(new RuntimeException());

		ResponseEntity<List<CommentDetails>> response = hackerController.getComments(1);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	@Test
	void getTopStories_returnsTopStories() {

		when(hackerNewsService.getTopStories()).thenReturn(stories);
		ResponseEntity<List<Story>> response = hackerController.getTopStories();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(stories, response.getBody());
	}

	@Test
	void getComments_withValidStoryId_returnsComments() {

		int storyId = 123789;
		List<CommentDetails> expectedComments = Arrays.asList(new CommentDetails("comment1", "user1"),
				new CommentDetails("comment2", "user2"));
		when(hackerNewsService.getComments(storyId)).thenReturn(expectedComments);

		ResponseEntity<List<CommentDetails>> response = hackerController.getComments(storyId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedComments, response.getBody());
	}

	@Test
	void getComments_NotFound() {
		
		int storyId = 123789;
		when(hackerNewsService.getComments(storyId)).thenReturn(Collections.emptyList());
		ResponseEntity<List<CommentDetails>> response = hackerController.getComments(storyId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(null, response.getBody());
	}

}