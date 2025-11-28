package br.feevale.model;


public class AtendimentoSimples extends Atendimento {
    
    private String tipoAtendimento; 
    private boolean processado;
    
    /**
     * Construtor para criar um atendimento simples.
     * @param numeroAtendimento Número único do atendimento
     * @param tipoAtendimento Tipo do atendimento
     */
    public AtendimentoSimples(int numeroAtendimento, String tipoAtendimento) {
        super(numeroAtendimento);
        this.tipoAtendimento = tipoAtendimento != null ? tipoAtendimento : "Atendimento";
        this.processado = false;
    }
    
    /**
     * Construtor simplificado.
     * @param numeroAtendimento Número único do atendimento
     */
    public AtendimentoSimples(int numeroAtendimento) {
        this(numeroAtendimento, "Atendimento Simples");
    }
    
    public String getTipoAtendimento() {
        return tipoAtendimento;
    }
    
    public boolean isProcessado() {
        return processado;
    }
    
   
    public void processar() {
        this.processado = true;
        // Processa todos os itens
        for (ItemAtendimento item : itens) {
            item.processar();
        }
    }
    
    @Override
    public String getResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════\n");
        sb.append("        ").append(tipoAtendimento.toUpperCase()).append("\n");
        sb.append("═══════════════════════════════════════\n");
        sb.append(String.format("Número: %d\n", numeroAtendimento));
        sb.append(String.format("Data/Hora: %s\n", getDataHoraFormatada()));
        sb.append(String.format("Status: %s\n", processado ? "Processado" : "Pendente"));
        sb.append("───────────────────────────────────────\n");
        sb.append("Itens:\n\n");
        
        for (ItemAtendimento item : itens) {
            sb.append(String.format("  • %s — R$ %.2f\n", item.getNome(), item.getValor()));
        }
        
        sb.append("───────────────────────────────────────\n");
        sb.append(String.format("TOTAL: R$ %.2f\n", getTotal()));
        sb.append("═══════════════════════════════════════");
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("%s #%d [%s] - R$ %.2f", 
                           tipoAtendimento, numeroAtendimento, 
                           processado ? "Processado" : "Pendente", getTotal());
    }
}
