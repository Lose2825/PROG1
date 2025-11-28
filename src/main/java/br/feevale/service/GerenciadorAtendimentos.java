package br.feevale.service;

import br.feevale.model.Atendimento;
import br.feevale.model.PedidoRestaurante;
import br.feevale.interfaces.Rastreavel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que gerencia todos os atendimentos do sistema.
 * Implementa o padrão Singleton para garantir uma única instância.
 * Responsável por:
 * - Gerar números únicos de atendimento
 * - Armazenar histórico de atendimentos
 * - Fornecer acesso aos atendimentos
 */
public class GerenciadorAtendimentos {
    
    // Instância única (Singleton)
    private static GerenciadorAtendimentos instancia;
    
    // Coleção de atendimentos (ArrayList - conceito de coleções)
    private final List<Atendimento> atendimentos;
    
    // Contador para gerar números únicos
    private int proximoNumero;
    
    /**
     * Construtor privado - padrão Singleton
     */
    private GerenciadorAtendimentos() {
        this.atendimentos = new ArrayList<>();
        this.proximoNumero = 1;
    }
    
    /**
     * Obtém a instância única do gerenciador (Singleton).
     * @return Instância única
     */
    public static synchronized GerenciadorAtendimentos getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorAtendimentos();
        }
        return instancia;
    }
    
    /**
     * Gera um número único para um novo atendimento.
     * @return Número único gerado
     */
    public synchronized int gerarNumeroAtendimento() {
        return proximoNumero++;
    }
    
    /**
     * Adiciona um atendimento ao histórico.
     * @param atendimento Atendimento a ser adicionado
     */
    public void adicionarAtendimento(Atendimento atendimento) {
        if (atendimento != null) {
            atendimentos.add(atendimento);
        }
    }
    
    /**
     * Obtém todos os atendimentos.
     * @return Lista de todos os atendimentos
     */
    public List<Atendimento> getAtendimentos() {
        return new ArrayList<>(atendimentos); // Retorna cópia para manter encapsulamento
    }
    
    /**
     * Obtém apenas os pedidos de restaurante.
     * Demonstra o uso de polimorfismo e casting.
     * @return Lista de pedidos de restaurante
     */
    public List<PedidoRestaurante> getPedidosRestaurante() {
        return atendimentos.stream()
                          .filter(a -> a instanceof PedidoRestaurante)
                          .map(a -> (PedidoRestaurante) a)
                          .collect(Collectors.toList());
    }
    
    /**
     * Obtém atendimentos rastreáveis (que implementam a interface Rastreavel).
     * Demonstra o uso de interfaces e polimorfismo.
     * @return Lista de atendimentos rastreáveis
     */
    public List<Rastreavel> getAtendimentosRastreaveis() {
        return atendimentos.stream()
                          .filter(a -> a instanceof Rastreavel)
                          .map(a -> (Rastreavel) a)
                          .collect(Collectors.toList());
    }
    
    /**
     * Busca um atendimento pelo número.
     * @param numero Número do atendimento
     * @return Atendimento encontrado ou null
     */
    public Atendimento buscarPorNumero(int numero) {
        return atendimentos.stream()
                          .filter(a -> a.getNumeroAtendimento() == numero)
                          .findFirst()
                          .orElse(null);
    }
    
    /**
     * Obtém o total de atendimentos realizados.
     * @return Quantidade de atendimentos
     */
    public int getTotalAtendimentos() {
        return atendimentos.size();
    }
    
    /**
     * Calcula o valor total de todos os atendimentos.
     * @return Valor total em reais
     */
    public double getValorTotalAtendimentos() {
        return atendimentos.stream()
                          .mapToDouble(Atendimento::getTotal)
                          .sum();
    }
    
    /**
     * Obtém o valor médio dos atendimentos.
     * @return Valor médio em reais
     */
    public double getValorMedio() {
        if (atendimentos.isEmpty()) {
            return 0.0;
        }
        return getValorTotalAtendimentos() / atendimentos.size();
    }
    
    /**
     * Limpa todos os atendimentos (útil para testes).
     */
    public void limparAtendimentos() {
        atendimentos.clear();
        proximoNumero = 1;
    }
    
    /**
     * Gera um relatório resumido dos atendimentos.
     * @return String com o relatório
     */
    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════\n");
        sb.append("      RELATÓRIO DE ATENDIMENTOS\n");
        sb.append("═══════════════════════════════════════\n\n");
        sb.append(String.format("Total de Atendimentos: %d\n", getTotalAtendimentos()));
        sb.append(String.format("Valor Total: R$ %.2f\n", getValorTotalAtendimentos()));
        sb.append(String.format("Valor Médio: R$ %.2f\n\n", getValorMedio()));
        
        sb.append("Últimos Atendimentos:\n");
        sb.append("───────────────────────────────────────\n");
        
        // Mostra os últimos 10 atendimentos
        int inicio = Math.max(0, atendimentos.size() - 10);
        for (int i = inicio; i < atendimentos.size(); i++) {
            sb.append(atendimentos.get(i).toString()).append("\n");
        }
        
        sb.append("═══════════════════════════════════════");
        return sb.toString();
    }
}
