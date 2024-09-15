package br.com.KevinVSantos.SimpleLibrary.controller;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.AbstractEntity;
import br.com.KevinVSantos.SimpleLibrary.service.AbstractService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractController<Id, Service extends AbstractService, Entity extends AbstractEntity> {

    @Autowired
    private Service service;

    @GetMapping
    public ResponseEntity get(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Id id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity post(@RequestBody @Valid Entity entity){
        return ResponseEntity.ok(service.create(entity));
    }

    @PutMapping
    public ResponseEntity put(@RequestBody @Valid Entity entity){
        return ResponseEntity.ok(service.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Id id){
        service.delete(id);
        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }


}
