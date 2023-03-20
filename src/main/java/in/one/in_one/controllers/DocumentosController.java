package in.one.in_one.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.one.in_one.models.Documentos;
import in.one.in_one.repository.DocumentosRepository;

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

@RestController
@RequestMapping("/api/documentos")
public class DocumentosController {

    Logger log = LoggerFactory.getLogger(DocumentosController.class);

    List<Documentos> documentos = new ArrayList<>();

    @Autowired // IoD IoC
    DocumentosRepository repository;

    @GetMapping
    public List<Documentos> index() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Documentos> create(@RequestBody Documentos documento) {
        log.info("cadastrando documento: " + documento);

        repository.save(documento);

        return ResponseEntity.status(HttpStatus.CREATED).body(documento);
    }

    @GetMapping("{id}")
    public ResponseEntity<Documentos> show(@PathVariable Long id) {
        log.info("buscando documento com id " + id);
        var documentoEncontrada = repository.findById(id);

        if (documentoEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(documentoEncontrada.get());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Documentos> destroy(@PathVariable Long id) {
        log.info("apagando documento com id " + id);
        var documentoEncontrada = repository.findById(id);

        if (documentoEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        repository.delete(documentoEncontrada.get());

        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Documentos> update(@PathVariable Long id, @RequestBody Documentos documento) {
        log.info("alterando documento com id " + id);
        var documentoEncontrada = repository.findById(id);

        if (documentoEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        documento.setDoc_id(id);

        repository.save(documento);

        return ResponseEntity.ok(documento);

    }

}
