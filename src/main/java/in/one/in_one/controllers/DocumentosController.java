package in.one.in_one.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.one.in_one.models.Documentos;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DocumentosController {

    Logger log = LoggerFactory.getLogger(DocumentosController.class);

    List<Documentos> documentos = new ArrayList<>();

    @GetMapping("/api/documentos")
    public List<Documentos> index() {
        return documentos;
    }

    @PostMapping("/api/documentos")
    public ResponseEntity<Documentos> create(@RequestBody Documentos documento) {
        log.info("cadastrando documento: " + documento);
        documento.setDoc_id(documentos.size() + 1l);
        documentos.add(documento);
        return ResponseEntity.status(HttpStatus.CREATED).body(documento);
    }

    @GetMapping("/api/documentos/{id}")
    public ResponseEntity<Documentos> show(@PathVariable Long id) {
        log.info("buscando documento com id " + id);
        var documentoSelecionado = documentos.stream().filter(d -> d.getDoc_id().equals(id)).findFirst();

        if (documentoSelecionado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(documentoSelecionado.get());

    }

    @DeleteMapping("/api/documentos/{id}")
    public ResponseEntity<Documentos> destroy(@PathVariable Long id) {
        log.info("apagando documento com id " + id);
        var documentoSelecionado = documentos.stream().filter(d -> d.getDoc_id().equals(id)).findFirst();

        if (documentoSelecionado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        documentos.remove(documentoSelecionado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/api/documentos/{id}")
    public ResponseEntity<Documentos> update(@PathVariable Long id, @RequestBody Documentos documento) {
        log.info("alterando documento de id " + id);
        var documentoSelecionado = documentos.stream().filter(d -> d.getDoc_id().equals(id)).findFirst();

        if (documentoSelecionado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        documentos.remove(documentoSelecionado.get());
        documento.setDoc_id(id);
        documentos.add(documento);

        return ResponseEntity.ok(documento);

    }

}
