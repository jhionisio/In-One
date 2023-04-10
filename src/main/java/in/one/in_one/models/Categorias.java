package in.one.in_one.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public abstract class Categorias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long cat_id;
    @NotNull
    private int cat_num;
    @NotBlank
    private String cat_color;
    @NotBlank
    @Size(max = 25)
    private String cat_name;
    @OneToMany
    private Documentos[] doc;
}
