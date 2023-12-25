package com.first.myFirst.service;

import com.first.myFirst.Entity.Calendrier;
import com.first.myFirst.Entity.Enseignant;
import com.first.myFirst.Repo.RepoCalendrier;
import com.first.myFirst.Repo.RepoEnseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCalendrier {
    @Autowired
    private RepoCalendrier repo;
    @Autowired
    private RepoEnseignant repoEnseignant;
private ServiceEnseignant serviceEnseignant;
    public ResponseEntity<String> save(Calendrier e, String id) {
        Optional<Enseignant> optionalEnseignant = repoEnseignant.findById(id);
        if (optionalEnseignant.isPresent()) {
            Enseignant enseignant = optionalEnseignant.get();
            if (enseignant.getCalendriers() == null) {
                enseignant.setCalendriers(new ArrayList<>());
            }

            List<Calendrier> enseignantCalandries= enseignant.getCalendriers();

            repo.save(e);
            enseignantCalandries.add(e);
            enseignant.setCalendriers(enseignantCalandries);
            repoEnseignant.save(enseignant);
            return new ResponseEntity<>("Calendrier saved successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Enseignant not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> Update(Calendrier e ,String id) {
        Optional<Calendrier> calendrierOptional = repo.findById(id);
        if (calendrierOptional.isPresent()) {
            Calendrier calendriersExisting = calendrierOptional.get();
            calendriersExisting.setAnnee(e.getAnnee());
            calendriersExisting.setMois(e.getMois());
            calendriersExisting.setEvenements(e.getEvenements());

             this.repo.save(calendriersExisting);
            return new ResponseEntity<>("successfully  ", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Calendrier not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
    public void delete(String id) {
        Optional<Calendrier> calendrierOptional = repo.findById(id);

        calendrierOptional.ifPresent(calendrier -> {
            // Récupérer l'enseignant associé à ce calendrier
            Optional<Enseignant> enseignantOptional = repoEnseignant.findByCalendriersContaining(calendrier);//cette function existe dans repo
            enseignantOptional.ifPresent(enseignant -> {
                // Supprimer le calendrier de la liste des calendriers de l'enseignant
                enseignant.getCalendriers().remove(calendrier);
                repoEnseignant.save(enseignant);
            });
            // Supprimer le calendrier de la base de données
            repo.deleteById(id);
        });

    }
}
