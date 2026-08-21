package br.edu.ifsp.biblioteca.domain;

public class Autor {

    private Long id;
    private String nome;

    public Autor(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
