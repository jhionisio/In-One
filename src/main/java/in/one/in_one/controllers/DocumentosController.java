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

import in.one.in_one.exception.RestNotFoundException;

@RestController
@Slf4j
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/documentos")
public class DocumentosController {

    Logger log = LoggerFactory.getLogger(DocumentosController.class);

    List<Documentos> documentos = new ArrayList<>();

    @Autowired // IoD IoC
    DocumentosRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Documentos>>> index(@RequestParam(required = false) Int docs, @PageableDefault(size = 5) Pageable pageable){

        List<EntityModel<Documentos>> documentosModel = new ArrayList<>();

        if (docs == null) {
            List<Documentos> documentos = repository.findAll(pageable).getContent();
            for (Documentos documento : documentos) {
                documentosModel.add(getDocumentosModel(documento));
            }
        } else {
            List<Documentos> documentos = repository.findByDocsContaining(docs, pageable).getContent();
            for (Documentos documento : documentos) {
                documentosModel.add(getDocumentosModel(documento));
            }
        }

        CollectionModel<EntityModel<Documentos>> collectionModel = CollectionModel.of(documentosModel);
        collectionModel.add(getSelfLink());
        return ResponseEntity.ok(collectionModel);

    }

    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Doc cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "erro na validação dos dados da requisição")
    })
    public ResponseEntity<EntityModel<Documentos>> create(@RequestBody @Valid Documentos documento){
        log.info("Cadastrando documento: " + documento);
        Documentos postObj = repository.save(documento);
        EntityModel<Documentos> documentoModel = getDocumentosModel(postObj);
        documentoModel.add(getSelfLink());
        documentoModel.add(getUpdateLink(postObj.getId()));
        documentoModel.add(getDeleteLink(postObj.getId()));
        return ResponseEntity.created(documentoModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(documentoModel);
    }
    
    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes do doc",
        description = "Retorna os dados de um doc com id especificado"
    )
    public ResponseEntity<EntityModel<Documentos>> show(@PathVariable Long id){
        log.info("Buscando documento com id " + id);
        Documentos documento = getCategoria(id);
        EntityModel<Documentos> documentoModel = getDocumentosModel(documento);
        documentoModel.add(getSelfLink());
        documentoModel.add(getUpdateLink(id));
        documentoModel.add(getDeleteLink(id));
        return ResponseEntity.ok(documentoModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Documentos> destroy(@PathVariable Long id){
        log.info("Apagando documento com id " + id);
        repository.delete(getCategoria(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Documentos>> update(@PathVariable Long id, @RequestBody @Valid Documentos documento){
        log.info("Alterando documento com id " + id);
        getCategoria(id);
        documento.setId(id);
        Documentos putObj = repository.save(documento);
        EntityModel<Documentos> documentoModel = getDocumentosModel(putObj);
        documentoModel.add(getSelfLink());
        documentoModel.add(getDeleteLink(putObj.getId()));
        return ResponseEntity.ok(documento);
    }

    private Documentos getDocumento(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento não existe"));
    }

}
