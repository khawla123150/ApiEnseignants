package com.first.myFirst.Controller;

import com.first.myFirst.Entity.Adresse;
import com.first.myFirst.Entity.Calendrier;
import com.first.myFirst.service.ServiceAdresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Adresse")
public class ControllerAdresse {
    @Autowired
    private ServiceAdresse service;
    @PostMapping("/add")
    public Adresse save(@RequestBody Adresse e){
        return service.save(e);
    }
    @PutMapping("/Update/{id}")
    public ResponseEntity<String> update(@RequestBody Adresse  e, @PathVariable String id ){return this.service.update(e,id);}
    @DeleteMapping("/delete/{id}")
    public void Delet(@PathVariable String id  ){
        this.service.Delete(id);
    }
}
