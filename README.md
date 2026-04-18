# Teste Prático - Iniflex (Gestão de Funcionários)

## 📌 Descrição

Projeto desenvolvido como parte do processo seletivo da Iniflex.
A aplicação realiza o gerenciamento de funcionários, aplicando regras de negócio como cálculos salariais, agrupamentos e filtros de dados.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17 (LTS)** – Utilização de Streams API e recursos modernos da linguagem
- **Maven** – Gerenciamento de dependências e build
- **JUnit 5** – Testes unitários para validação das regras de negócio
- **BigDecimal** – Precisão em cálculos financeiros

---

## 🧱 Estrutura do Projeto

O projeto foi organizado com separação de responsabilidades:

```id="a1n2k3"
src/main/java/
 ├── model/      # Entidades (Pessoa, Funcionario)
 ├── service/    # Regras de negócio
 ├── util/       # Mock de dados
 └── Principal   # Classe principal (execução)

src/test/java/
 └── service/    # Testes unitários
```

---

## ⚙️ Funcionalidades

- Inserção dos funcionários conforme especificação
- Remoção do funcionário “João”
- Formatação de datas (`dd/MM/yyyy`) e valores monetários (padrão brasileiro)
- Aplicação de aumento salarial de 10%
- Agrupamento de funcionários por função
- Listagem de funcionários agrupados
- Filtro de aniversariantes (meses 10 e 12)
- Identificação do funcionário com maior idade
- Ordenação alfabética
- Cálculo do total de salários
- Cálculo de salários mínimos por funcionário

---

## ▶️ Como Executar

### Pré-requisitos

- Java 17
- Maven instalado

### Executar testes

```bash id="run1"
mvn clean test
```

### Executar aplicação

```bash id="run2"
mvn exec:java -Dexec.mainClass="Principal"
```

---

## 🧪 Testes

Foram implementados testes unitários para validar:

- Aplicação de aumento salarial
- Cálculo do total de salários

---

## 💡 Observações

O projeto foi estruturado com foco em:

- Clareza e organização do código
- Separação de responsabilidades
- Uso de boas práticas em Java

---

## 👨‍💻 Autor

Diego Colombari Rapichan
