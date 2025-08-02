package br.com.alura.literalura.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.literalura.literalura.model.Autor;
import br.com.alura.literalura.literalura.model.Idioma;
import br.com.alura.literalura.literalura.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);

    @Query("SELECT DISTINCT a FROM Livro l JOIN l.autores a ORDER BY a.nome")
    List<Autor> listarAutoresBuscados();

    List<Livro> findByIdioma(Idioma idioma);
}
