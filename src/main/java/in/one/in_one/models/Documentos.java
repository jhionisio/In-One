package in.one.in_one.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Documentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long doc_id;
    @NotNull
    private int doc_num;
    @NotBlank
    @Size(min = 11, max = 11)
    private String doc_digit;
    @NotBlank
    @Size(max = 225)
    private String doc_name;
    @NotNull
    private String doc_body;

    protected Documentos() {
    }

    public Documentos(Long doc_id, int doc_num, String doc_digit, String doc_name, String doc_body) {
        this.doc_id = doc_id;
        this.doc_num = doc_num;
        this.doc_digit = doc_digit;
        this.doc_name = doc_name;
        this.doc_body = doc_body;
    }

    public Long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Long doc_id) {
        this.doc_id = doc_id;
    }

    public int getDoc_num() {
        return doc_num;
    }

    public void setDoc_num(int doc_num) {
        this.doc_num = doc_num;
    }

    public String getDoc_digit() {
        return doc_digit;
    }

    public void setDoc_digit(String doc_digit) {
        this.doc_digit = doc_digit;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDoc_body() {
        return doc_body;
    }

    public void setDoc_body(String doc_body) {
        this.doc_body = doc_body;
    }

}
