package br.com.vet.petapi.exception;

public class AnimalNaoEncontradoException extends RuntimeException {
    public AnimalNaoEncontradoException(Long id) {
        super("Animal não encontrado com id " + id);
    }
}