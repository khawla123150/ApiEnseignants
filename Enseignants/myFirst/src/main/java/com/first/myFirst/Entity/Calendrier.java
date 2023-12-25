package com.first.myFirst.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Calendrier {
     @Id
    private String id;
     private String evenements;
     private String annee;
     private String mois;

}
