package br.feevale.model;

public enum StatusPedido {
    PENDENTE("Pendente"),
    EM_PREPARO("Em Preparo"),
    PRONTO("Pronto"),
    ENTREGUE("Entregue");
    
    private final String descricao;
    
    StatusPedido(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Retorna o próximo status na sequência, ou null se já estiver no último.
     * @return Próximo status ou null
     */
    public StatusPedido proximo() {
        StatusPedido[] valores = values();
        int indiceAtual = this.ordinal();
        
        if (indiceAtual < valores.length - 1) {
            return valores[indiceAtual + 1];
        }
        return null; 
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}
