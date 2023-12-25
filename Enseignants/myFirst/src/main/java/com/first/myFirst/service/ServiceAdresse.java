package com.first.myFirst.service;

import com.first.myFirst.Entity.Adresse;
import com.first.myFirst.Repo.RepoAdresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAdresse {
    @Autowired
    private RepoAdresse reop;
    public Adresse save(Adresse e){ return this.reop.save(e);}
    public ResponseEntity<String> update(Adresse e, String id ){
        Optional<Adresse>Optionaladresse=reop.findById(id);
        if(Optionaladresse.isPresent()) {
            Adresse AdresseExisting = Optionaladresse.get();
            AdresseExisting.setZipPostal(e.getZipPostal());
            AdresseExisting.setVille(e.getVille());
            AdresseExisting.setPays(e.getPays());
            AdresseExisting.setRoute(e.getRoute());
            AdresseExisting.setNumber(e.getNumber());
            AdresseExisting.setComplement(e.getComplement());
            AdresseExisting.setStage(e.getStage());
            this.reop.save(AdresseExisting);

            return  new ResponseEntity<>("Calendrier saved successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(" Calendrier not found with ID: " + id, HttpStatus.NOT_FOUND);
    }
    public void  Delete(String id ){
        this.reop.deleteById(id);
    }
}
