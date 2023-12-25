package com.first.myFirst.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Enseignant {
    @Id
    private String id ;
    private String nom;
    private String prenom;
    private String email;
    private String genre;
    private String dateNaissance;
    private String dateEntrer;
    private Boolean activated;
    private String categorie;
    @DBRef
    public   Adresse adresse;
    @DBRef
    private List<Calendrier> calendriers ;

    public  Adresse getAdresse() {
        return this.adresse;
    }

    public   void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    public List<Calendrier> getCalendriers() {
        return this.calendriers;
    }
    public void setCalendriers(List<Calendrier> calendriers) {
        this.calendriers = calendriers;
    }

    public void setOneCalendrier(Calendrier calendrier) {
        if (this.calendriers == null) {
            this.calendriers = new ArrayList<>();
        }
        this.calendriers.add(calendrier);
    }
}
