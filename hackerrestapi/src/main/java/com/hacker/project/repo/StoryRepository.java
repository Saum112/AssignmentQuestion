package com.hacker.project.repo;

import org.springframework.stereotype.Repository;

import com.hacker.project.model.Story;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

}
