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
    public ResponseEntity<Categoria> create(@RequestBody @Valid Categoria categoria){
        log.info("Cadastrando categoria: " + categoria);
        repository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id){
        log.info("Buscando categoria com id " + id);
        return ResponseEntity.ok(getCategoria(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Categoria> destroy(@PathVariable Long id){
        log.info("Apagando categoria com id " + id);
        repository.delete(getCategoria(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody @Valid Categoria categoria){
        log.info("Alterando categoria com id " + id);
        getCategoria(id);
        categoria.setId(id);
        repository.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    private Categoria getCategoria(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não existe"));
    }
}
