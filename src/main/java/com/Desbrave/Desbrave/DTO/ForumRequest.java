package com.Desbrave.Desbrave.DTO;




import com.Desbrave.Desbrave.constants.Titulo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor      
@AllArgsConstructor
@Getter
@Setter
public class ForumRequest {
    @NotBlank
    private String titulo; 

    private String descricao;




    public String getTitulo() {
        return String.valueOf(Titulo.valueOf(titulo.toUpperCase()));
    }
}
