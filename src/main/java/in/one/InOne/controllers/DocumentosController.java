package in.one.InOne.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.one.InOne.models.Documentos;

@RestController
public class DocumentosController {

    @GetMapping("/inOne/api/docs")
    public List<Documentos> GetAll() {

        List<Documentos> docs = new ArrayList<Documentos>();

        var doc1 = new Documentos(1, 12456789, "x", "RG", "Corpo do documento");
        var doc2 = new Documentos(2, 24567890, "x", "RG", "Corpo do documento");
        var doc3 = new Documentos(3, 45678900, "x", "RG", "Corpo do documento");
        var doc4 = new Documentos(4, 56789000, "x", "RG", "Corpo do documento");

        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);
        docs.add(doc4);

        return docs;
    }

    @GetMapping("/inOne/api/docs/listOne")
    public Documentos GetOne(int param) {

        List<Documentos> docs = new ArrayList<Documentos>();

        var doc1 = new Documentos(1, 12456789, "x", "RG", "Corpo do documento");
        var doc2 = new Documentos(2, 24567890, "x", "RG", "Corpo do documento");
        var doc3 = new Documentos(3, 45678900, "x", "RG", "Corpo do documento");
        var doc4 = new Documentos(4, 56789000, "x", "RG", "Corpo do documento");

        var one = new Documentos();

        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);
        docs.add(doc4);

        for (int i = 0; i < docs.size(); i++) {
            var j = docs.get(i);

            if (j.getDoc_id() == param) {
                one = j;
            }

        }

        return one;

    }

}
