package com.first.myFirst.Repo;
import com.first.myFirst.Entity.Calendrier;
import com.first.myFirst.Entity.Enseignant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RepoEnseignant extends MongoRepository<Enseignant,String>{
    Optional<Enseignant> findByCalendriersContaining(Calendrier calendrier);
}


