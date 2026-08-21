package br.edu.ifsp.biblioteca;

import br.edu.ifsp.biblioteca.domain.Livro;

public class BibliotecaApplication {

    public static void main(String[] args) {
        Livro livro = new Livro(1L,
                "9788594318671",
                "Dom Quixote",
                1615
        );

        Livro livro2 = new Livro(2L,
                "1111111111111",
                "Dom Casmurro",
                2026
        );

        Livro livro3 = new Livro(3L,
                "1111111111112",
                "Crime e castigo",
                2025
        );

        System.out.println(livro);
    }
}
