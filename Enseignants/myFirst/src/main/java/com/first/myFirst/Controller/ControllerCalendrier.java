package com.first.myFirst.Controller;

import com.first.myFirst.Entity.Calendrier;
import com.first.myFirst.service.ServiceCalendrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Calendreier")
public class ControllerCalendrier {
    @Autowired
    private ServiceCalendrier service;

    public ControllerCalendrier(ServiceCalendrier service) {
        this.service = service;
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<String> save(@RequestBody Calendrier e, @PathVariable  String id ){
        return  this.service.save(e,id);

    }
    @PutMapping("/Update/{id}")
    public ResponseEntity<String> update(@RequestBody Calendrier e ,@PathVariable String id){  return this.service.Update(e,id);
    }
    @DeleteMapping("/delete/{id}")
    public void Delet(@PathVariable String id   ){
        this.service.delete(id);
    }
}
