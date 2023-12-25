package com.first.myFirst.Repo;

import com.first.myFirst.Entity.Calendrier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepoCalendrier extends MongoRepository<Calendrier,String> {

}
