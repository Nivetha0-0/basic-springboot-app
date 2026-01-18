package com.project.jobposts.repository;

import java.util.List;

import com.project.jobposts.model.Jobpostings;

public interface SearchRepository {
    List<Jobpostings> findByText(String text);
    }

