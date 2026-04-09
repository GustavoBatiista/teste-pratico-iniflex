# Teste prático — Iniflex

Este projeto foi desenvolvido em Java utilizando Maven, seguindo o roteiro proposto no teste técnico.
O objetivo foi aplicar conceitos fundamentais de orientação a objetos, manipulação de coleções, organização de código e boas práticas de desenvolvimento.

A solução implementa o modelo `Pessoa` e `Funcionario`, trabalhando com lista de objetos, remoção de registros, reajuste salarial, agrupamento por função, ordenação, filtros por data e geração de relatórios formatados.

Procurei manter o código organizado em uma estrutura simples e clara, priorizando legibilidade, separação de responsabilidades e facilidade de manutenção, de forma semelhante ao que encontramos em projetos reais.

---

## Tecnologias utilizadas

- Java 17
- Maven
- JUnit 5

---

## Funcionalidades implementadas

O projeto executa as seguintes operações:

- criação da lista inicial de funcionários
- remoção de funcionário específico da lista
- impressão da lista com formatação de data e salário no padrão brasileiro
- aplicação de reajuste salarial de 10%
- agrupamento de funcionários por função
- identificação de aniversariantes dos meses de outubro e dezembro
- identificação do funcionário mais velho
- ordenação da lista em ordem alfabética
- cálculo do total dos salários
- cálculo de quantos salários mínimos cada funcionário recebe

---

## Como executar

Pré-requisitos:

- JDK 17 ou superior
- Maven 3.8 ou superior

Para compilar e executar o projeto:

mvn -q compile exec:java

## Executar testes

O projeto possui testes unitários cobrindo as principais regras de negócio:

mvn test

---

## Estrutura do projeto


- model → entidades do domínio
- service → regras de negócio e operações sobre a lista de funcionários
- util → formatação de datas e valores monetários no padrão brasileiro
- Principal → execução do fluxo solicitado no enunciado
