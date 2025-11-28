package br.feevale.model;

public class Servico extends ItemAtendimento {
    
    private String tipoServico; // Ex: "Pagamento", "Recarga", "Consulta"
    private String codigoReferencia; // Ex: código do ticket
    
    /**
     * Construtor para criar um serviço.
     * @param nome Nome do serviço
     * @param valor Valor do serviço
     * @param imagePath Caminho da imagem
     * @param tipoServico Tipo do serviço
     * @param codigoReferencia Código de referência
     */
    public Servico(String nome, double valor, String imagePath, String tipoServico, String codigoReferencia) {
        super(nome, valor, imagePath);
        this.tipoServico = tipoServico;
        this.codigoReferencia = codigoReferencia;
    }
    
    
    public Servico(String nome, double valor, String imagePath) {
        this(nome, valor, imagePath, "Serviço", "");
    }
    
    public String getTipoServico() {
        return tipoServico;
    }
    
    public String getCodigoReferencia() {
        return codigoReferencia;
    }
    
    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }
    
    @Override
    public String getDescricao() {
        String descricao = String.format("%s (%s) - R$ %.2f", nome, tipoServico, valor);
        if (codigoReferencia != null && !codigoReferencia.isEmpty()) {
            descricao += " [" + codigoReferencia + "]";
        }
        return descricao;
    }
    
    @Override
    public boolean processar() {

        System.out.println("Processando serviço: " + nome);
        if (codigoReferencia != null && !codigoReferencia.isEmpty()) {
            System.out.println("Código de referência: " + codigoReferencia);
        }
        return true;
    }
}
