package org.api.java.api_v2_2826502.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString   
@Entity
@Table(name="products")

public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nombre no debe tener espacios en blanco")
    @NotEmpty(message = "no puede estar basio")
    private  String name;

    @Size(min=10, max = 20, message = "descripcion fuera de rango")
    private String description;

    @NonNull
    @Min(50)
    private  Double price;
    public void delete() {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
