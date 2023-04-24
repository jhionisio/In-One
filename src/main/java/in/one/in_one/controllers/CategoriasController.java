package in.one.in_one.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

/*    @GetMapping
    public Page<Categoria> index(@RequestParam(required = false) String name, @PageableDefault(size = 5) Pageable pageable){
        if (name == null) return repository.findAll(pageable);
        return repository.findByDocsContaining(name, pageable);
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
*/

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Categoria>>> index(@RequestParam(required = false) String name, @PageableDefault(size = 5) Pageable pageable){

        List<EntityModel<Categoria>> categoriasModel = new ArrayList<>();

        if (name == null) {
            List<Categoria> categorias = repository.findAll(pageable).getContent();
            for (Categoria categoria : categorias) {
                categoriasModel.add(getCategoriaModel(categoria));
            }
        } else {
            List<Categoria> categorias = repository.findByDocsContaining(name, pageable).getContent();
            for (Categoria categoria : categorias) {
                categoriasModel.add(getCategoriaModel(categoria));
            }
        }

        CollectionModel<EntityModel<Categoria>> collectionModel = CollectionModel.of(categoriasModel);
        collectionModel.add(getSelfLink());
        return ResponseEntity.ok(collectionModel);

    }

    @PostMapping
    public ResponseEntity<EntityModel<Categoria>> create(@RequestBody @Valid Categoria categoria){
        log.info("Cadastrando categoria: " + categoria);
        Categoria postObj = repository.save(categoria);
        EntityModel<Categoria> categoriaModel = getCategoriaModel(postObj);
        categoriaModel.add(getSelfLink());
        categoriaModel.add(getUpdateLink(postObj.getId()));
        categoriaModel.add(getDeleteLink(postObj.getId()));
        return ResponseEntity.created(categoriaModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(categoriaModel);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Categoria>> show(@PathVariable Long id){
        log.info("Buscando categoria com id " + id);
        Categoria categoria = getCategoria(id);
        EntityModel<Categoria> categoriaModel = getCategoriaModel(categoria);
        categoriaModel.add(getSelfLink());
        categoriaModel.add(getUpdateLink(id));
        categoriaModel.add(getDeleteLink(id));
        return ResponseEntity.ok(categoriaModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Categoria> destroy(@PathVariable Long id){
        log.info("Apagando categoria com id " + id);
        repository.delete(getCategoria(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Categoria>> update(@PathVariable Long id, @RequestBody @Valid Categoria categoria){
        log.info("Alterando categoria com id " + id);
        getCategoria(id);
        categoria.setId(id);
        Categoria putObj = repository.save(categoria);
        EntityModel<Categoria> categoriaModel = getCategoriaModel(putObj);
        categoriaModel.add(getSelfLink());
        categoriaModel.add(getDeleteLink(putObj.getId()));
        return ResponseEntity.ok(categoria);
    }

    private Categoria getCategoria(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não existe"));
    }
}
