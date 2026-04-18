# Teste Prático - Iniflex

## Descrição

Projeto desenvolvido em Java para atender aos requisitos propostos no teste técnico da Iniflex.
O sistema realiza operações sobre uma lista de funcionários, incluindo manipulação de dados, cálculos e organização das informações.

---

## Estrutura do Projeto

O projeto foi organizado seguindo o princípio de separação de responsabilidades:

```
src/
 ├── model/      # Entidades (Pessoa, Funcionario)
 ├── service/    # Regras de negócio
 ├── util/       # Mock de dados
 ├── test/       # Testes unitários (JUnit)
 └── Principal.java  # Classe de execução
```

---

## Funcionalidades Implementadas

- ✔ Inserção de funcionários conforme tabela fornecida
- ✔ Remoção do funcionário "João"
- ✔ Impressão formatada:
  - Data no formato `dd/MM/yyyy`
  - Valores monetários no padrão brasileiro

- ✔ Aplicação de aumento salarial de 10%
- ✔ Agrupamento de funcionários por função (`Map<String, List<Funcionario>>`)
- ✔ Listagem de funcionários agrupados por função
- ✔ Filtro de aniversariantes dos meses 10 e 12
- ✔ Identificação do funcionário com maior idade
- ✔ Ordenação alfabética por nome
- ✔ Cálculo do total dos salários
- ✔ Cálculo de quantos salários mínimos cada funcionário recebe

---

## Testes

Foram implementados testes unitários utilizando JUnit para validação das principais regras de negócio:

- Aplicação de aumento salarial
- Cálculo do total de salários

---

## ▶ Como Executar

### Pré-requisitos

- Java 8 ou superior

### Compilação

```bash
javac Principal.java
```

### Execução

```bash
java Principal
```

---

## Decisões Técnicas

- Utilização de `BigDecimal` para operações financeiras, garantindo precisão
- Uso de `Streams` para operações em coleções
- Separação da lógica em uma camada de serviço (`FuncionarioService`)
- Criação de classe utilitária (`FuncionarioMock`) para simular dados
- Implementação de testes unitários para validar regras críticas

---

## Autor

Diego Rapichan

---
