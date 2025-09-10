text
# Projeto Restaurante - Módulo Estacionamento

## Visão Geral

Este projeto em Java implementa um sistema simples de estacionamento para um restaurante, integrando-se a um banco de dados Oracle.  
Possui as entidades (tabelas) `vehicles` e `payments`, e oferece as seguintes funcionalidades principais:

- Registrar entrada de veículo  
- Registrar saída de veículo, calculando tarifa de R$ 40,00 por hora (primeira hora cheia cobrada mesmo que parcial)  
- Listar veículos atualmente estacionados  

Foi implementada a exceção personalizada `EstacionamentoException` e aplicados os padrões DAO e Singleton para conexão com o banco.

---

## Requisitos

- Java 11 ou superior instalado.  
- Servidor Oracle acessível.  
- Driver Oracle JDBC compatível com seu JDK (ex: `ojdbc8.jar` ou `ojdbc11.jar`).  

Faça o download do driver JDBC Oracle na página oficial de drivers:  
https://www.oracle.com/database/technologies/jdbc-ucp-21c-downloads.html

---

## Configuração do Banco de Dados

Antes de compilar, configure os dados de conexão em:  
`src/br/com/restaurante/estacionamento/dao/DBConnection.java`

// --- DADOS DE CONEXÃO ORACLE ---
private static final String HOST = "seu_host_oracle"; // ex: localhost ou oracle.fiap.com.br
private static final String PORTA = "1521";
private static final String SID = "ORCL"; // ou o Service Name do Oracle
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";

text

---

## Compilação

1. Crie a pasta `lib` na raiz do projeto.  
2. Copie o arquivo do driver Oracle JDBC (ex: `ojdbc8.jar`) para `lib`.  
3. Abra o terminal na raiz do projeto (onde está a pasta `src`).  

### Linux/macOS

javac -cp "lib/ojdbc8.jar" -d out $(find src -name "*.java")

text

### Windows (CMD/PowerShell)

javac -cp "lib\ojdbc8.jar" -d out -sourcepath src src\br\com\restaurante\estacionamento*.java src\br\com\restaurante\estacionamento\dao*.java src\br\com\restaurante\estacionamento\exception*.java src\br\com\restaurante\estacionamento\model*.java

text

---

## Execução

### Linux/macOS

java -cp "out:lib/ojdbc8.jar" br.com.restaurante.estacionamento.Main

text

### Windows (CMD/PowerShell)

java -cp "out;lib\ojdbc8.jar" br.com.restaurante.estacionamento.Main

text

---

## Testando a Aplicação

No menu interativo da aplicação, as opções são:

1. Registrar entrada de veículo:  
   Informe a placa do veículo para registrar sua entrada. Caso o veículo já esteja registrado mas esteja "inativo" (saído), ele será reativado para um cliente recorrente.

2. Registrar saída de veículo:  
   Informe a placa para registrar a saída, e será calculado o valor a ser pago (R$ 40,00 por hora). A primeira hora é cobrada integralmente, mesmo que o tempo seja inferior.

3. Listar veículos estacionados:

Para simular a passagem do tempo, aguarde alguns minutos entre registrar entrada e saída para observar a cobrança proporcional ao tempo (com arredondamento da primeira hora).
