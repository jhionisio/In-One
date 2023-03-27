package in.one.in_one.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public abstract class Categorias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cat_id;
    private int cat_num;
    private String cat_color;
    private String cat_name;

    public Categorias(Long cat_id, int cat_num, String cat_color, String cat_name) {
        this.cat_id = cat_id;
        this.cat_num = cat_num;
        this.cat_color = cat_color;
        this.cat_name = cat_name;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public int getCat_num() {
        return cat_num;
    }

    public void setCat_num(int cat_num) {
        this.cat_num = cat_num;
    }

    public String getCat_color() {
        return cat_color;
    }

    public void setCat_color(String cat_color) {
        this.cat_color = cat_color;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
}
