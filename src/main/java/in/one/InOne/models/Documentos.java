package in.one.InOne.models;

public class Documentos {

    private long doc_id;
    private int doc_num;
    private String doc_digit;
    private String doc_name;
    private String doc_body;

    public Documentos() {
    }

    public Documentos(long doc_id, int doc_num, String doc_digit, String doc_name, String doc_body) {
        this.doc_id = doc_id;
        this.doc_num = doc_num;
        this.doc_digit = doc_digit;
        this.doc_name = doc_name;
        this.doc_body = doc_body;
    }

    public long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(long doc_id) {
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
