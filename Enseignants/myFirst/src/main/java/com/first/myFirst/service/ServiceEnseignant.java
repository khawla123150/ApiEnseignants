package com.first.myFirst.service;

import com.first.myFirst.Entity.Adresse;
import com.first.myFirst.Entity.Calendrier;
import com.first.myFirst.Entity.Enseignant;
import com.first.myFirst.Repo.RepoAdresse;
import com.first.myFirst.Repo.RepoCalendrier;
import com.first.myFirst.Repo.RepoEnseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEnseignant {
    @Autowired
    private RepoEnseignant Repo;
    @Autowired
    private RepoAdresse adresseRepo;
    @Autowired
    private RepoCalendrier repoCalendrier;

    public Enseignant save(Enseignant enseignant) {
        if (enseignant != null && enseignant.getAdresse() != null) {
            Adresse enseignantAdresse = enseignant.getAdresse();
            List<Calendrier> ensigCalandries = enseignant.getCalendriers();
            if (ensigCalandries == null) {
                ensigCalandries = new ArrayList<>();
            }
            // Sauvegarde des Calendriers associés à l'Enseignant
            for (Calendrier calendrier : ensigCalandries) {
                repoCalendrier.save(calendrier);
            }
            Adresse enregistreAdresse = adresseRepo.save(enseignantAdresse);
            enseignant.setAdresse(enregistreAdresse);
            enseignant.setCalendriers(ensigCalandries);
            return Repo.save(enseignant);
        } else {
            throw new IllegalArgumentException("Enseignant or address cannot be null");
        }
    }
    public Enseignant update(Enseignant enseignant, String id) {
        Optional<Enseignant> enseignantOptional = Repo.findById(id);
        if (enseignantOptional.isPresent()) {
            Enseignant existingEnseignant = enseignantOptional.get();
            existingEnseignant.setNom(enseignant.getNom());
            existingEnseignant.setPrenom(enseignant.getPrenom());
            existingEnseignant.setGenre(enseignant.getGenre());
            existingEnseignant.setEmail(enseignant.getEmail());
            existingEnseignant.setDateNaissance(enseignant.getDateNaissance());
            existingEnseignant.setDateEntrer(enseignant.getDateEntrer());
            existingEnseignant.setActivated(enseignant.getActivated());
            existingEnseignant.setCategorie(enseignant.getCategorie());
            if (enseignant.getAdresse() != null) {
                Adresse enseignantAdresse = enseignant.getAdresse();
                Adresse enregistreAdresse = adresseRepo.save(enseignantAdresse);
                existingEnseignant.setAdresse(enregistreAdresse);
            }
            if (enseignant.getCalendriers() != null) {
                List<Calendrier> enseignantCalandriers = enseignant.getCalendriers();

                // Sauvegarde des calendriers associés à l'enseignant
                List<Calendrier> calendriersEnregistres = repoCalendrier.saveAll(enseignantCalandriers);

                // Mettre à jour l'enseignant avec les calendriers sauvegardés
                existingEnseignant.setCalendriers(calendriersEnregistres);
            }


            return Repo.save(existingEnseignant);
        } else {
            throw new IllegalArgumentException("Enseignant not found with ID: " + id);
        }
    }
    public void Delete(String id) {

        Optional<Enseignant> optionalEnseignant = this.Repo.findById(id);

        if (optionalEnseignant.isPresent()) {
            Enseignant enseignant = optionalEnseignant.get();
            Adresse enseignantAdresse = enseignant.getAdresse();
            List<Calendrier> ensigCalandries=enseignant.getCalendriers();

            if (enseignantAdresse != null ) {
                adresseRepo.deleteById(enseignantAdresse.getId());
                for (Calendrier calendrier : ensigCalandries) {
                    repoCalendrier.deleteById(calendrier.getId());
                }

            }

            this.Repo.deleteById(id);
        } else {

            throw new IllegalArgumentException("Enseignant not found with ID: " + id);
        }
    }
}
