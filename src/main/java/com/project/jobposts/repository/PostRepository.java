package com.project.jobposts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.jobposts.model.Jobpostings;

public interface PostRepository extends MongoRepository<Jobpostings,String> {


}
