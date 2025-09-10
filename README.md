# Estacionamento

Projeto: Restaurante - Módulo Estacionamento
Domínio do produto: www.restaurante.com.br

Módulo: Estacionamento

Resumo
Projeto Java "sem Maven" (apenas arquivos .java) que implementa um sistema simples de estacionamento para um restaurante, conectando-se a um banco de dados Oracle.

Duas entidades (tabelas): vehicles, payments

Funcionalidades (mínimo 3): Registrar entrada, registrar saída (calcula tarifa de R$ 40,00/hora), listar veículos estacionados.

Exception customizada: EstacionamentoException

Padrões aplicados: DAO, Singleton (DBConnection).

Requisitos
Java 11+ instalado.

Acesso a um servidor de banco de dados Oracle.

Baixar o driver Oracle JDBC (por exemplo: ojdbc8.jar ou ojdbc11.jar) e colocá-lo em uma pasta lib na raiz do projeto.

Link para download: Página Oficial de Drivers JDBC da Oracle (baixe a versão compatível com seu JDK).

Configuração da Conexão
Este é o passo mais importante. Antes de compilar, você precisa editar o arquivo src/br/com/restaurante/estacionamento/dao/DBConnection.java e preencher as constantes com os dados de acesso ao seu ambiente Oracle:

Java

// --- DADOS DE CONEXÃO ORACLE ---
// Preencha com os dados do seu ambiente Oracle
private static final String HOST = "seu_host_oracle"; // ex: oracle.fiap.com.br ou localhost
private static final String PORTA = "1521";
private static final String SID = "ORCL";             // ou o Service Name
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";
Compilação e Execução (Linha de Comando)
Crie uma pasta chamada lib na raiz do projeto.

Coloque o arquivo .jar do driver Oracle (ex: ojdbc8.jar) dentro da pasta lib.

Abra o terminal na raiz do projeto (onde está a pasta src) e rode os seguintes comandos:

Compilar
Bash

# No Linux/macOS
javac -cp "lib/ojdbc8.jar" -d out $(find src -name "*.java")

# No Windows (CMD/PowerShell)
javac -cp "lib\ojdbc8.jar" -d out -sourcepath src src\br\com\restaurante\estacionamento\*.java src\br\com\restaurante\estacionamento\dao\*.java src\br\com\restaurante\estacionamento\exception\*.java src\br\com\restaurante\estacionamento\model\*.java
Executar
Bash

# No Linux/macOS
java -cp "out:lib/ojdbc8.jar" br.com.restaurante.estacionamento.Main

# No Windows (use ';' como separador no classpath)
java -cp "out;lib\ojdbc8.jar" br.com.restaurante.estacionamento.Main
Observações sobre Integração com o Banco
A aplicação se conectará ao servidor Oracle configurado no arquivo DBConnection.java. Garanta que há conectividade de rede (firewall, VPN, etc.) entre sua máquina e o servidor do banco.

As tabelas vehicles e payments são criadas automaticamente na primeira conexão, caso ainda não existam no schema do usuário configurado.

Entregáveis
Código fonte completo (arquivos .java).

README.md com instruções de configuração e execução (este arquivo).

Como Testar Rapidamente
Configure, compile e rode a aplicação conforme as instruções acima.

No menu, escolha a opção 1 para registrar a entrada de um veículo (ex: ABC-1234).

Escolha a opção 3 para verificar que o veículo está listado.

Espere um minuto para simular a passagem do tempo.

Escolha a opção 2 e digite a mesma placa (ABC-1234) para registrar a saída.

A aplicação calculará e exibirá o valor a ser pago (R$ 40,00 por hora, com a primeira hora cheia sendo cobrada mesmo por alguns minutos).

Se tentar registrar a entrada do mesmo veículo novamente, a aplicação irá "reativar" o registro antigo, permitindo o fluxo de um cliente recorrente.
