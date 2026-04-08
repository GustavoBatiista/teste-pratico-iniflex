package br.com.iniflex.model;

import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {

    private final String nome;
    private final LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = Objects.requireNonNull(nome, "nome");
        this.dataNascimento = Objects.requireNonNull(dataNascimento, "dataNascimento");
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        return "Pessoa{nome='" + nome + "', dataNascimento=" + dataNascimento + "}";
    }
}
