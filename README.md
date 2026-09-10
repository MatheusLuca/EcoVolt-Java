# EcoVolt Java

Projeto Java da sprint Domain Driven Design Using Java, alinhado ao MER EcoVolt (`T_EV_*`).

## Objetivo

O EcoVolt ajuda usuarios a controlar contas de energia, missoes, dicas, acoes sustentaveis e pontuacao, incentivando o consumo consciente por meio de gamificacao (pontos, niveis e ranking).

## Ferramentas utilizadas

- Java JDK 17 ou superior
- Maven (gerenciamento de dependencias e build)
- Oracle Database + driver JDBC `ojdbc17` (versao definida no `pom.xml`)
- IDE sugerida: IntelliJ IDEA (tambem funciona em Eclipse, NetBeans ou VS Code com extensoes Java)
- Oracle SQL Developer (para executar o script do banco)

## Descricao das funcionalidades

O sistema roda em menu de console (`EcoVoltApp`) e oferece as seguintes funcionalidades:

### Usuarios
- Cadastrar usuario (nome, e-mail, senha, data de nascimento, genero, cidade, estado, pais).
- Listar usuarios cadastrados.
- Atualizar dados de um usuario.
- Remover usuario.

### Contas de energia
- Cadastrar conta de energia (usuario, data de referencia, leitura, consumo kWh, valor, comprovante, status de pagamento).
- Listar contas de energia.
- Atualizar conta de energia.
- Remover conta de energia.

### Missoes
- Cadastrar missao (nome, descricao, pontos premio, data de inicio e fim).
- Listar missoes.
- Remover missao.

### Dicas
- Cadastrar dica de sustentabilidade (com usuario autor).
- Listar dicas.

### Acoes sustentaveis
- Cadastrar acao sustentavel (nome, descricao, pontos base).
- Listar acoes sustentaveis.

### Gamificacao e relatorios
- Pontuar usuario por economia: compara as duas ultimas contas e gera pontos pela reducao de consumo, atualizando o nivel do usuario.
- Ranking de usuarios: ordena os usuarios pela pontuacao acumulada.
- Resumo de consumo do usuario: total e media de kWh e de valor pago.
- Listar pontuacoes registradas.
- Listar niveis disponiveis.

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

## Estrutura do projeto

- `model`: entidades do MER
- `dao`: CRUD JDBC com `PreparedStatement`
- `service`: regras de negocio
- `view`: menu console (`EcoVoltApp`)
- `exception`: excecoes proprias
- `conexao`: `ConnectionFactory` com usuario/senha no codigo

## Procedimentos para rodar a aplicacao

### Pre-requisitos
- Java JDK 17 ou superior instalado e configurado (`java -version`).
- Maven instalado (`mvn -version`) ou uso do Maven embutido na IDE.
- Acesso a um banco Oracle e ao Oracle SQL Developer.

### Como importar o projeto
1. Clone ou baixe este repositorio.
2. No IntelliJ IDEA: `File > Open` e selecione a pasta do projeto (que contem o `pom.xml`). A IDE reconhece automaticamente como projeto Maven.
   - No Eclipse: `File > Import > Existing Maven Projects`.
   - No VS Code: abra a pasta e instale o "Extension Pack for Java".
3. Aguarde o Maven baixar as dependencias (driver `ojdbc17`).

### Passo a passo da execucao
1. Abra o `src/main/resources/database.sql` no Oracle SQL Developer e execute o script para criar as tabelas, sequences e dados iniciais.
2. Confira e ajuste, se necessario, o usuario/senha e a URL de conexao em `src/main/java/br/com/EcoVolt/conexao/ConnectionFactory.java`.
3. Execute a classe principal `br.com.EcoVolt.view.EcoVoltApp` (metodo `main`).
4. Utilize o menu numerado no console para acessar as funcionalidades.
5. (Opcional) Execute `br.com.EcoVolt.TesteEcoVolt` para validar as regras de negocio e o fluxo com o banco.
