package br.com.KevinVSantos.SimpleLibrary.controller;

import br.com.KevinVSantos.SimpleLibrary.config.CachingConfiguration;
import br.com.KevinVSantos.SimpleLibrary.domain.entity.AbstractEntity;
import br.com.KevinVSantos.SimpleLibrary.service.AbstractService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractController<Id, Service extends AbstractService, Entity extends AbstractEntity> {

    @Autowired
    private Service service;

    @GetMapping
    @Cacheable(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME)
    public ResponseEntity get(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Id id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @CacheEvict(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME, allEntries = true)
    public ResponseEntity post(@RequestBody @Valid Entity entity){
        return ResponseEntity.ok(service.create(entity));
    }

    @PutMapping
    @CacheEvict(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME, allEntries = true)
    public ResponseEntity put(@RequestBody @Valid Entity entity){
        return ResponseEntity.ok(service.update(entity));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME, allEntries = true)
    public ResponseEntity delete(@PathVariable("id") Id id){
        service.delete(id);
        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }


}
