package br.com.vet.petapi.service;

import br.com.vet.petapi.exception.AnimalNaoEncontradoException;
import br.com.vet.petapi.dto.AnimalRequestDTO;
import br.com.vet.petapi.dto.AnimalResponseDTO;
import br.com.vet.petapi.model.Animal;
import br.com.vet.petapi.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public AnimalResponseDTO criar(AnimalRequestDTO request) {
        Animal animal = new Animal(
                request.nome(),
                request.especie(),
                request.nomeTutor(),
                request.idade()
        );
        return AnimalResponseDTO.fromEntity(repository.save(animal));
    }

    public List<AnimalResponseDTO> listar() {
        return repository.findAll().stream()
                .map(AnimalResponseDTO::fromEntity)
                .toList();
    }

    public AnimalResponseDTO buscarPorId(Long id) {
        return AnimalResponseDTO.fromEntity(buscarEntidade(id));
    }

    public AnimalResponseDTO atualizar(Long id, AnimalRequestDTO request) {
        Animal animal = buscarEntidade(id);
        animal.setNome(request.nome());
        animal.setEspecie(request.especie());
        animal.setNomeTutor(request.nomeTutor());
        animal.setIdade(request.idade());
        return AnimalResponseDTO.fromEntity(repository.save(animal));
    }

    public void deletar(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Animal buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AnimalNaoEncontradoException(id));
    }
}