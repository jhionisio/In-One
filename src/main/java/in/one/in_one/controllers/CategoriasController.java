package in.one.in_one.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.one.in_one.models.Categorias;
import in.one.in_one.repository.CategoriasRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriasController {
    Logger log = LoggerFactory.getLogger(CategoriasController.class);

    List<Categorias> categorias = new ArrayList<>();

    @Autowired // IoD IoC
    CategoriasRepository repository;

    @GetMapping
    public List<Categorias> index() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categorias> create(@RequestBody Categorias categoria) {
        log.info("cadastrando categoria: " + categoria);

        repository.save(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categorias> show(@PathVariable Long id) {
        log.info("buscando categoria com id " + id);
        var categoriaEncontrada = repository.findById(id);

        if (categoriaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categoriaEncontrada.get());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Categorias> destroy(@PathVariable Long id) {
        log.info("apagando categoria com id " + id);
        var categoriaEncontrada = repository.findById(id);

        if (categoriaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        repository.delete(categoriaEncontrada.get());

        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Categorias> update(@PathVariable Long id, @RequestBody Categorias categoria) {
        log.info("alterando categoria com id " + id);
        var categoriaEncontrada = repository.findById(id);

        if (categoriaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        categoria.setCat_id(id);

        repository.save(categoria);

        return ResponseEntity.ok(categoria);

    }
}
