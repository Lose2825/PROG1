package br.feevale.model;

import br.feevale.interfaces.Rastreavel;


public class PedidoRestaurante extends Atendimento implements Rastreavel {
    
    private StatusPedido status;
    private String observacoes;
    
    /**
     * Construtor para criar um pedido de restaurante.
     * @param numeroAtendimento Número único do atendimento
     */
    public PedidoRestaurante(int numeroAtendimento) {
        super(numeroAtendimento);
        this.status = StatusPedido.PENDENTE;
        this.observacoes = "";
    }
    
    /**
     * Construtor com observações.
     * @param numeroAtendimento Número único do atendimento
     * @param observacoes Observações do cliente
     */
    public PedidoRestaurante(int numeroAtendimento, String observacoes) {
        super(numeroAtendimento);
        this.status = StatusPedido.PENDENTE;
        this.observacoes = observacoes != null ? observacoes : "";
    }
    
    // Implementação dos métodos da interface Rastreavel
    @Override
    public StatusPedido getStatus() {
        return status;
    }
    
    @Override
    public void setStatus(StatusPedido novoStatus) {
        if (novoStatus != null) {
            this.status = novoStatus;
        }
    }
    
    @Override
    public void avancarStatus() {
        StatusPedido proximo = status.proximo();
        if (proximo != null) {
            this.status = proximo;
        }
    }
    
    @Override
    public boolean isConcluido() {
        return status == StatusPedido.ENTREGUE;
    }
    
    public String getObservacoes() {
        return observacoes;
    }
    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes != null ? observacoes : "";
    }
    
    @Override
    public String getResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════\n");
        sb.append("        PEDIDO RESTAURANTE\n");
        sb.append("═══════════════════════════════════════\n");
        sb.append(String.format("Número: %d\n", numeroAtendimento));
        sb.append(String.format("Data/Hora: %s\n", getDataHoraFormatada()));
        sb.append(String.format("Status: %s\n", status.toString()));
        sb.append("───────────────────────────────────────\n");
        sb.append("Itens do pedido:\n\n");
        
        for (ItemAtendimento item : itens) {
            sb.append(String.format("  • %s — R$ %.2f\n", item.getNome(), item.getValor()));
        }
        
        if (!observacoes.isEmpty()) {
            sb.append("\nObservações: ").append(observacoes).append("\n");
        }
        
        sb.append("───────────────────────────────────────\n");
        sb.append(String.format("TOTAL: R$ %.2f\n", getTotal()));
        sb.append("═══════════════════════════════════════");
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("Pedido #%d [%s] - R$ %.2f", 
                           numeroAtendimento, status.toString(), getTotal());
    }
}
