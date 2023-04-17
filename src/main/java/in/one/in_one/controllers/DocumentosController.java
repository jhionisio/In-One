package in.one.in_one.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import in.one.in_one.models.Documentos;
import in.one.in_one.repository.DocumentosRepository;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.one.in_one.exception.RestNotFoundException;

@RestController
@RequestMapping("/api/documentos")
public class DocumentosController {

    Logger log = LoggerFactory.getLogger(DocumentosController.class);

    List<Documentos> documentos = new ArrayList<>();

    @Autowired // IoD IoC
    DocumentosRepository repository;

    @GetMapping
    public Page<Documentos> index(@RequestParam(required = false) Int docs, @PageableDefault(size = 5) Pageable pageable){
        if (docs == null) return repository.findAll(pageable);
        return repository.findByDocsContaining(docs, pageable);
    }

    @PostMapping
    public ResponseEntity<Documentos> create(@RequestBody @Valid Documentos documento){
        log.info("Cadastrando documento: " + documento);
        repository.save(documento);
        return ResponseEntity.status(HttpStatus.CREATED).body(documento);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Documentos> show(@PathVariable Long id){
        log.info("Buscando documento com id " + id);
        return ResponseEntity.ok(getDocumento(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Documentos> destroy(@PathVariable Long id){
        log.info("Apagando documento com id " + id);
        repository.delete(getDocumento(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Documentos> update(@PathVariable Long id, @RequestBody @Valid Documentos documento){
        log.info("Alterando documento com id " + id);
        getDocumento(id);
        documento.setId(id);
        repository.save(documento);
        return ResponseEntity.ok(documento);
    }

    private Documentos getDocumento(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento não existe"));
    }

}
