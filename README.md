# LiterAlura

## Descrição do Projeto

O **LiterAlura** é uma aplicação de console em Java com Spring Boot criada como solution do desafio da Alura/Oracle ONE (Java Backend). A ideia é consultar a API Gutendex para buscar livros, armazená-los localmente e permitir diversas consultas — como buscar por título, listar livros e autores registrados, filtrar autores vivos em determinado ano e listar livros por idioma, sempre mantendo boas práticas.

## Status do Projeto

- ✅ Funcional: aplicação executa o menu no console e interage com o banco de dados.

## Funcionalidades

- **Buscar livro pelo título**: consulta a API externa e salva novos livros, evitando duplicação.
- **Listar livros registrados**: mostra todos os livros persistidos, ordenados por título.
- **Listar autores registrados**: exibe autores cadastrados e seus livros.
- **Listar autores vivos em um determinado ano**: permite filtrar autores que ainda estavam vivos num ano informado.
- **Listar livros por idioma**: busca livros conforme idioma selecionado (suporta múltiplos códigos, como pt, en, fr, etc).
- **Fallback de idioma**: caso o idioma não seja reconhecido ou retorne da API, é usado `DESCONHECIDO`, evitando falhas.

## Como acessar

- A aplicação é executada via console. Após compilação, iniciar a classe principal para exibir o menu interativo com as opções acima.

## Tecnologias Utilizadas

- **Backend**: Java 17, Spring Boot 3.x  
- **Persistência**: Spring Data JPA, Hibernate  
- **Banco de Dados**: PostgreSQL (pode usar outros compatíveis com Spring Data JPA)  
- **Consumo de API**: Gutendex API (biblioteca pública de livros gratuitos)  
- **Construção**: Maven  
- **Utilitários**: Enum para idiomas, tratamento de fallback, comparação, ordenação

## O que aprendi com esse projeto

- Como estruturar uma aplicação de backend em formato de CLI robusta e resiliente;  
- Implementação de enum com tratamento eficiente de valores desconhecidos;  
- Uso de Spring Data JPA com busca por enum e mapeamento de coleções bidirecionais;  
- Resolução de problemas comuns: LazyInitialization, entidades detached, conversão de listas, formatação de dados.

## Onde tive mais dificuldade

- Tratar corretamente o carregamento de entidades relacionadas (`autores`) com sessões Hibernate.  
- Converter corretamente a lista de idiomas da API (strings entre colchetes) para enum Java, sem quebrar a persistência.  
- Abordar restrições do banco (CHECK constraint) para permitir valores como “sv” ou “DESCONHECIDO”.

## Pessoas Contribuidoras

- Projeto desenvolvido por Robson Durães

## Licença

Este projeto está licenciado sob a **Licença MIT**.
