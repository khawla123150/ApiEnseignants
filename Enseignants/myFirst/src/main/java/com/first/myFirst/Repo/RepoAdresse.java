package com.first.myFirst.Repo;

import com.first.myFirst.Entity.Adresse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface RepoAdresse extends MongoRepository<Adresse,String> {
}
