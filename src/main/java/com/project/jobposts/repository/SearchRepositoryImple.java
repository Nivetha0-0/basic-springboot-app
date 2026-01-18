package com.project.jobposts.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.project.jobposts.model.Jobpostings;

@Component
public class SearchRepositoryImple implements SearchRepository {
    

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Jobpostings> findByText(String text){

        final List<Jobpostings> posts= new ArrayList<>();

        MongoDatabase database = client.getDatabase("springboot");
        MongoCollection<Document> collection = database.getCollection("posts");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
                                        new Document("index", "jobsearch")
                                        .append("text",                                      
                                        new Document("query", text)
                                        .append("path", Arrays.asList("techs", "profile", "desc")))), 
                                        new Document("$sort", 
                                        new Document("exp", 1L)), 
                                        new Document("$limit", 5L)));
                                    
        result.forEach(doc -> posts.add(converter.read(Jobpostings.class,doc)));

        return posts;
    }
}
