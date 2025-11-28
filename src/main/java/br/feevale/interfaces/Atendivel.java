package br.feevale.interfaces;


public interface Atendivel {
    
    /**
     * Obtém o nome do item.
     * @return Nome do item
     */
    String getNome();
    
    /**
     * Obtém o valor do item.
     * @return Valor em reais
     */
    double getValor();
    
    /**
     * Obtém a descrição do item.
     * @return Descrição detalhada
     */
    String getDescricao();
    
    /**
     * Processa o item (ex: preparar produto, processar pagamento).
     * @return true se processado com sucesso
     */
    boolean processar();
}
