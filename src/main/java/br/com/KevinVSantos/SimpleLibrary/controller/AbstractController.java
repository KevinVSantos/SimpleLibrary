package br.com.KevinVSantos.SimpleLibrary.controller;

import br.com.KevinVSantos.SimpleLibrary.config.CachingConfig;
import br.com.KevinVSantos.SimpleLibrary.domain.entity.AbstractEntity;
import br.com.KevinVSantos.SimpleLibrary.service.AbstractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractController<Id, Service extends AbstractService, Entity extends AbstractEntity> {

    @Autowired
    private Service service;

    @GetMapping
    @Operation(description = "Busca todas as entidades.")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Lista das entidades encontradas")
    )
    @Cacheable(cacheResolver = CachingConfig.CACHE_RESOLVER_NAME)
    public ResponseEntity<List<Entity>> get(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(description = "Busca a entidade por ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entidade encontrada"),
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada")
    })
    public ResponseEntity get(@PathVariable("id") Id id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(description = "Cria uma nova entidade.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entidade criada com sucesso"),
            @ApiResponse(responseCode = "409", description = "Entidade já existe")
    })
    @CacheEvict(cacheResolver = CachingConfig.CACHE_RESOLVER_NAME, allEntries = true)
    public ResponseEntity post(@RequestBody @Valid Entity entity){
        return ResponseEntity.ok(service.create(entity));
    }

    @PutMapping
    @Operation(description = "Altera uma entidade já existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entidade alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada")
    })
    @CacheEvict(cacheResolver = CachingConfig.CACHE_RESOLVER_NAME, allEntries = true)
    public ResponseEntity put(@RequestBody @Valid Entity entity){
        return ResponseEntity.ok(service.update(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleta a entidade por ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entidade removida com suesso"),
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada")
    })
    @CacheEvict(cacheResolver = CachingConfig.CACHE_RESOLVER_NAME, allEntries = true)
    public ResponseEntity delete(@PathVariable("id") Id id){
        service.delete(id);
        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }


}
