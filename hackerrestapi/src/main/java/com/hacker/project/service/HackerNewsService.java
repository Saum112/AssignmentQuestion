package com.hacker.project.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hacker.project.constant.Constants;
import com.hacker.project.model.Comment;
import com.hacker.project.model.CommentDetails;
import com.hacker.project.model.Story;
import com.hacker.project.repo.StoryRepository;
import org.springframework.cache.annotation.Cacheable;

@Service
public class HackerNewsService {
	private static final Logger LOG = LoggerFactory.getLogger(HackerNewsService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private StoryRepository storyRepository;

	@Cacheable(cacheNames = "topStories")
	public List<Story> getTopStories() {

		LOG.info("Fetching top stories from the API");

		String url = Constants.HACKER_NEWS_TOP_STORIES_URL;
		List<Integer> storyIds = restTemplate.getForObject(url, List.class);

		List<Story> stories = new ArrayList<>();
		for (Integer id : storyIds) {
			String storyUrl = Constants.HACKER_NEWS_API_URL + id + ".json";
			Story story = restTemplate.getForObject(storyUrl, Story.class);
			stories.add(story);
		}

		stories.sort((s1, s2) -> {
			if (s2.getTime() - s1.getTime() == 0) {
				return s2.getScore() - s1.getScore();
			}
			return (int) (s2.getTime() - s1.getTime());
		});

		List<Story> topStories = stories.subList(0, 10);
		storyRepository.saveAll(topStories);

		return topStories;
	}

	public List<Story> getPastStories() {
		return storyRepository.findAll();
	}

	@Cacheable(cacheNames = "comments")
	public List<CommentDetails> getComments(int storyId) {
		String url = Constants.HACKER_NEWS_API_URL + storyId + ".json";
		Comment story = restTemplate.getForObject(url, Comment.class);
		List<Long> commentIds = story.getKids();

		List<Comment> comments = new ArrayList<>();
		for (Long commentId : commentIds) {
			String commentUrl = Constants.HACKER_NEWS_API_URL + commentId + ".json";
			Comment comment = restTemplate.getForObject(commentUrl, Comment.class);
			comments.add(comment);
		}

		comments.sort((c1, c2) -> c2.getDescendants() - c1.getDescendants());
		List<CommentDetails> commentDetails = new ArrayList<>();
		for (Comment comment : comments) {
			CommentDetails details = new CommentDetails(comment.getText(), comment.getBy());
			commentDetails.add(details);
		}
		if (commentDetails.size() <= 10) {
			return commentDetails;
		} else {
			return commentDetails.subList(0, 10);
		}

	}

}
