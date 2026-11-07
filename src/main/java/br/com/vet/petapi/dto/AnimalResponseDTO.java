package br.com.vet.petapi.dto;

import br.com.vet.petapi.model.Animal;

public record AnimalResponseDTO(
        Long id,
        String nome,
        String especie,
        String nomeTutor,
        Integer idade
) {
    public static AnimalResponseDTO fromEntity(Animal animal) {
        return new AnimalResponseDTO(
                animal.getId(),
                animal.getNome(),
                animal.getEspecie(),
                animal.getNomeTutor(),
                animal.getIdade()
        );
    }
}