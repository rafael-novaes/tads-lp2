package br.edu.ifsp.biblioteca.domain;

public class Livro {

    private Long id;
    private String isbn;
    private String titulo;
    private Integer anoPublicacao;

    public Livro(String isbn, String titulo, Integer anoPublicacao) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

//    @Override
//    public String toString() {
//        return "Livro{" +
//                "id=" + id +
//                ", isbn='" + isbn + '\'' +
//                ", titulo='" + titulo + '\'' +
//                ", anoPublicacao=" + anoPublicacao +
//                '}';
//    }
}
