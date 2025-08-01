package br.com.alura.literalura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.literalura.literalura.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
