# EcoVolt Java

Projeto Java da sprint Domain Driven Design Using Java, alinhado ao MER EcoVolt (`T_EV_*`).

## Objetivo

O EcoVolt ajuda usuarios a controlar contas de energia, missoes, dicas, acoes sustentaveis e pontuacao.

## Tabelas do MER usadas no projeto

- `T_EV_USUARIO`
- `T_EV_NIVEL`
- `T_EV_RANKING`
- `T_EV_CONTA_ENERGIA`
- `T_EV_PONTUACAO`
- `T_EV_MISSAO`
- `T_EV_OBJ_MIS`
- `T_EV_USU_MIS`
- `T_EV_CONQUISTA`
- `T_EV_USU_CON`
- `T_EV_ACA_SUSTENTAVEIS`
- `T_EV_DICA`
- `T_EV_INTERACAO`
- `T_EV_RECOMPENSA`
- `T_EV_LOGIN_DIARIO`
- `T_EV_RECUPERACAO_LOGIN`

O script completo esta em `src/main/resources/database.sql`.

## Estrutura

- `model`: entidades do MER
- `dao`: CRUD JDBC com `PreparedStatement`
- `service`: regras de negocio
- `view`: menu console (`EcoVoltApp`)
- `exception`: excecoes proprias
- `conexao`: `ConnectionFactory` com usuario/senha no codigo

## Como executar

1. Abra o projeto Maven na IDE.
2. Execute `src/main/resources/database.sql` no Oracle SQL Developer.
3. Confira usuario/senha em `ConnectionFactory`.
4. Rode `br.com.EcoVolt.view.EcoVoltApp`.
5. Rode `br.com.EcoVolt.TesteEcoVolt` para testar regras sem banco.

## Ferramentas

- Java 17+
- Maven
- Oracle + ojdbc17
