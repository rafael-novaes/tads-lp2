package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;

import java.util.*;

public class LivroRepositoyEmMemoria implements ILivroRepository{

    private final Map <Long, Livro> livros = new HashMap<>();
    private Long sequenciaId = 0l;

    @Override
    public Livro salvar(Livro livro) {

        if (livro.getId() == null) {
            livro.setId(++this.sequenciaId);
        }

        this.livros.put(livro.getId(), livro);
        return livro;
    }

    @Override
    public List<Livro> listarTodos() {
        return new ArrayList<>(this.livros.values());
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        Livro l = this.livros.get(id);
        //return l == null ? Optional.empty() : Optional.of(l);
        return Optional.ofNullable(l);
    }

    @Override
    public Optional<Livro> buscarPorIsbn(String isbn) {

//        for (Livro livro : livros.values()) {
//            if (livro.getIsbn().equalsIgnoreCase(isbn)) {
//                return Optional.of(livro);
//            }
//        }

        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());

        for(int i = 0; i < colecaoLivros.size(); i++) {
            if (colecaoLivros.get(i).getIsbn().equalsIgnoreCase(isbn)) {
                return Optional.of(colecaoLivros.get(i));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {

        List<Livro> livrosEncontrados = new ArrayList<>();

        for (Livro livro : livros.values()) {
            if (livro.getTitulo().contains(titulo))
                livrosEncontrados.add(livro);
        }

//        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());

//        for(int i = 0; i < colecaoLivros.size(); i++) {
//            if (colecaoLivros.get(i).getTitulo().contains(titulo)) {
//                livrosEncontrados.add(colecaoLivros.get(i));
//            }
//        }

        return livrosEncontrados;
    }
}
