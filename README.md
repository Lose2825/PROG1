# PROG1
 Sistema de Quiosque de Autoatendimento

Trabalho Final - Programação Orientada a Objetos em Java

  Descrição do Projeto

Sistema orientado a objetos desenvolvido em Java com JavaFX para gerenciamento de quiosques de autoatendimento (restaurantes, lancherias, etc.). O projeto aplica os principais conceitos de POO:

-  Encapsulamento: Modificadores de acesso adequados em todas as classes
-  Herança: Hierarquia de classes (ItemAtendimento → Produto/Servico, Atendimento → PedidoRestaurante/AtendimentoSimples)
-  Polimorfismo: Tratamento uniforme via interfaces e classes abstratas
-  Classes Abstratas: ItemAtendimento e Atendimento como bases
-  Interfaces: Atendivel e Rastreavel como contratos
-  Coleções: ArrayList para gerenciar itens e atendimentos

  Arquitetura do Sistema



  Funcionalidades Implementadas

 Interface do Cliente
-  Visualizar menu de produtos
-  Adicionar itens ao carrinho
-  Remover itens do carrinho
-  Visualizar valor total do pedido
-  Confirmar pedido (gera número único de atendimento)
-  Exibir número do pedido atual
-  Navegação para modo gerenciamento

 Interface do Gestor
-  Visualizar todos os atendimentos realizados
-  Visualizar detalhes de cada atendimento
-  Atualizar status dos pedidos manualmente
-  Avançar status dos pedidos automaticamente
-  Gerar relatório com estatísticas:
  - Total de atendimentos
  - Valor total arrecadado
  - Valor médio por atendimento
-  Navegação de volta para modo cliente

 Sistema de Gerenciamento
-  Geração automática de números únicos de atendimento
-  Armazenamento de histórico de atendimentos (ArrayList)
-  Rastreamento de status (PENDENTE → EM_PREPARO → PRONTO → ENTREGUE)
-  Padrão Singleton para o GerenciadorAtendimentos

  Como Executar

 Pré-requisitos
- Java JDK 11 ou superior
- Maven 3.6 ou superior

 Compilação e Execução

```bash
# Navegar até o diretório do projeto
cd demo

# Compilar o projeto
mvn clean compile

# Executar a aplicação
mvn javafx:run
```

 Execução via IDE
1. Abra o projeto na sua IDE (IntelliJ IDEA, Eclipse, VSCode)
2. Execute a classe `br.feevale.Main`

  Conceitos de POO Aplicados

 1. Encapsulamento
- Atributos privados/protegidos em todas as classes
- Acesso controlado via getters/setters
- Exemplo: `ItemAtendimento` com atributos `protected`

 2. Herança
- `Produto` e `Servico` herdam de `ItemAtendimento`
- `PedidoRestaurante` e `AtendimentoSimples` herdam de `Atendimento`
- Reutilização de código e especialização de comportamento

 3. Polimorfismo
- Tratamento uniforme de `ItemAtendimento` (pode ser Produto ou Servico)
- Tratamento uniforme de `Atendimento` (pode ser PedidoRestaurante ou AtendimentoSimples)
- Método abstrato `getResumo()` com implementações específicas

 4. Classes Abstratas
- `ItemAtendimento`: define estrutura base para itens
- `Atendimento`: define estrutura base para atendimentos
- Métodos abstratos forçam implementação nas subclasses

 5. Interfaces
- `Atendivel`: contrato para itens que podem ser atendidos
- `Rastreavel`: contrato para entidades com status rastreável
- Permite múltipla "herança" de comportamento

 6. Coleções (ArrayList)
- `List<ItemAtendimento>` no Atendimento
- `List<Atendimento>` no GerenciadorAtendimentos
- Uso de Streams para filtros e operações

  Interface Gráfica

 Tela do Cliente
- Lista de produtos disponíveis
- Carrinho de compras
- Exibição do total e número do pedido
- Botão para confirmar pedido
- Botão para acessar gerenciamento

 Tela de Gerenciamento
- Dashboard com estatísticas
- Lista de todos os atendimentos
- Detalhes do atendimento selecionado
- Controles para atualizar status
- Botão para gerar relatório
- Botão para voltar ao modo cliente

  Exemplo de Uso

1. Cliente faz um pedido:
   - Seleciona "Hambúrguer" e clica em "Adicionar ao Carrinho"
   - Seleciona "Refrigerante" e adiciona também
   - Confirma o pedido
   - Recebe número de atendimento #1

2. Gestor gerencia o pedido:
   - Acessa modo gerenciamento
   - Vê o Pedido #1 na lista
   - Seleciona o pedido e visualiza detalhes
   - Avança status: PENDENTE → EM_PREPARO → PRONTO → ENTREGUE
   - Gera relatório para ver estatísticas

  Extensibilidade

O sistema foi projetado para ser facilmente extensível:

- Novos tipos de itens: Basta criar uma classe que herde de `ItemAtendimento`
- Novos tipos de atendimento**: Basta criar uma classe que herde de `Atendimento`
- Novos comportamentos: Basta criar novas interfaces e implementá-las
- Novos status: Basta adicionar valores no enum `StatusPedido`
