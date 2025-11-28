package br.feevale.interfaces;

import br.feevale.model.StatusPedido;


public interface Rastreavel {
    
    /**
     * Obtém o status atual.
     * @return Status atual do pedido
     */
    StatusPedido getStatus();
    
    /**
     * Atualiza o status.
     * @param novoStatus Novo status a ser definido
     */
    void setStatus(StatusPedido novoStatus);
    
    /**
     * Avança para o próximo status na sequência.
     */
    void avancarStatus();
    
    /**
     * Verifica se o atendimento foi concluído.
     * @return true se concluído
     */
    boolean isConcluido();
}
