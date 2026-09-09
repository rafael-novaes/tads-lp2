package br.edu.ifsp.biblioteca;

import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.repository.ILivroRepository;
import br.edu.ifsp.biblioteca.repository.LivroRepositoryEmMemoria;

import java.util.List;
import java.util.Optional;


public class BibliotecaApplication {
    public static void main(String[] args) {

        Livro livro = new Livro(
            "9788508145607",
            "Dom Casmurro",
            2026
        );

        Livro livro2 = new Livro(
            "9788573264579",
            "Dom Quixote",
            2026
        );

        Livro livro3 = new Livro(
            "9788573266467",
            "Crime e Castigo",
            2026
        );

        ILivroRepository livroRepository = new LivroRepositoryEmMemoria();

        livroRepository.salvar(livro);
        livroRepository.salvar(livro);
        livroRepository.salvar(livro2);
        livroRepository.salvar(livro3);

        Optional<Livro> livro1Optional = livroRepository.buscarPorId(1L);
        Optional<Livro> livro2Optional = livroRepository.buscarPorId(10L);


        if (livro1Optional.isPresent()) {

            Livro l1 = livro1Optional.get();
            System.out.println("Livro com ID 1 encontrado: " + l1);
        }

        if (livro2Optional.isPresent()) {

            Livro l2 = livro2Optional.get();
            System.out.println("Livro com ID 1 encontrado: " + l2);
        } else {
            System.out.println("Livro com ID 10 não encontrado!");
        }

        List<Livro> encontrados = livroRepository.buscarPorTitulo("Casmurro");

        System.out.println(encontrados);

//        System.out.println(livro);
    }
}
