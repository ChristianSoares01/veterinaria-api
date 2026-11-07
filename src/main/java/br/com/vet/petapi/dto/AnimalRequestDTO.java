package br.com.vet.petapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnimalRequestDTO(

        @NotBlank(message = "O nome do animal é obrigatório")
        String nome,

        @NotBlank(message = "A espécie é obrigatória")
        String especie,

        @NotBlank(message = "O nome do tutor é obrigatório")
        String nomeTutor,

        @NotNull(message = "A idade é obrigatória")
        @Min(value = 0, message = "A idade deve ser um número inteiro igual ou superior a zero")
        Integer idade
) {
}