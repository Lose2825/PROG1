package br.feevale.model;

import br.feevale.interfaces.Atendivel;


public abstract class ItemAtendimento implements Atendivel {
   
    protected String nome;
    protected double valor;
    protected String imagePath;
    
    /**
     * Construtor protegido - apenas subclasses podem instanciar.
     * @param nome Nome do item
     * @param valor Valor do item
     * @param imagePath Caminho para a imagem do item
     */
    protected ItemAtendimento(String nome, double valor, String imagePath) {
        this.nome = nome;
        this.valor = valor;
        this.imagePath = imagePath;
    }
    
    // Implementação dos métodos da interface Atendivel
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public double getValor() {
        return valor;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    
    @Override
    public abstract String getDescricao();
    
    @Override
    public abstract boolean processar();
    
    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", valor);
    }
}
