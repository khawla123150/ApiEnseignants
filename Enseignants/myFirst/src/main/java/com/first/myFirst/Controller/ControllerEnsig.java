package com.first.myFirst.Controller;

import com.first.myFirst.Entity.Enseignant;
import com.first.myFirst.service.ServiceEnseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Enseignant")
public class ControllerEnsig {
    @Autowired
    private ServiceEnseignant service;

@PostMapping("/add")
    public Enseignant save(@RequestBody Enseignant e){
    return service.save(e);
}
    @DeleteMapping("/delete/{id}")
    public void Delet(@PathVariable String id ){
        this.service.Delete(id);
    }
    @PutMapping("/update/{id}")
    public Enseignant Update(@RequestBody Enseignant e,@PathVariable String id){
        return service.update(e,id);
    }
}
