package br.com.vet.petapi.controller;

import br.com.vet.petapi.dto.AnimalRequestDTO;
import br.com.vet.petapi.dto.AnimalResponseDTO;
import br.com.vet.petapi.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> criar(@Valid @RequestBody AnimalRequestDTO request) {
        AnimalResponseDTO criado = service.criar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criado.id()).toUri();
        return ResponseEntity.created(location).body(criado);
    }

    @GetMapping
    public List<AnimalResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public AnimalResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AnimalResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AnimalRequestDTO request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}