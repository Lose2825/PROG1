package br.feevale.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Atendimento {
    
    // Atributos protegidos - acessíveis pelas subclasses (encapsulamento)
    protected int numeroAtendimento;
    protected LocalDateTime dataHora;
    protected List<ItemAtendimento> itens;
    
    /**
     * Construtor protegido - apenas subclasses podem instanciar.
     * @param numeroAtendimento Número único do atendimento
     */
    protected Atendimento(int numeroAtendimento) {
        this.numeroAtendimento = numeroAtendimento;
        this.dataHora = LocalDateTime.now();
        this.itens = new ArrayList<>();
    }
    
    // Métodos comuns a todos os atendimentos
    public int getNumeroAtendimento() {
        return numeroAtendimento;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public List<ItemAtendimento> getItens() {
        return new ArrayList<>(itens); // Retorna cópia para manter encapsulamento
    }
    
    public void addItem(ItemAtendimento item) {
        if (item != null) {
            itens.add(item);
        }
    }
    
    public void removeItem(ItemAtendimento item) {
        itens.remove(item);
    }
    
    public void clear() {
        itens.clear();
    }
    
    /**
     * Calcula o valor total do atendimento.
     * @return Valor total
     */
    public double getTotal() {
        return itens.stream()
                    .mapToDouble(ItemAtendimento::getValor)
                    .sum();
    }
    
    /**
     * Método abstrato que deve ser implementado pelas subclasses.
     * Cada tipo de atendimento terá seu próprio formato de resumo.
     * @return 
     */
    public abstract String getResumo();
    
    /**
     * Formata a data/hora do atendimento.
     * @return Data/hora formatada
     */
    public String getDataHoraFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataHora.format(formatter);
    }
    
    @Override
    public String toString() {
        return String.format("Atendimento #%d - %s - R$ %.2f", 
                           numeroAtendimento, getDataHoraFormatada(), getTotal());
    }
}
