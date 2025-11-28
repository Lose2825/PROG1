package br.feevale.model;

public class Produto extends ItemAtendimento {
    
    private String categoria; // Ex: "Lanche", "Bebida", "Sobremesa"
    
    /**
     * Construtor para criar um produto.
     * @param nome Nome do produto
     * @param valor Valor do produto
     * @param imagePath Caminho da imagem
     * @param categoria Categoria do produto
     */
    public Produto(String nome, double valor, String imagePath, String categoria) {
        super(nome, valor, imagePath);
        this.categoria = categoria;
    }
    
    
    public Produto(String nome, double valor, String imagePath) {
        this(nome, valor, imagePath, "Produto");
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    @Override
    public String getDescricao() {
        return String.format("%s (%s) - R$ %.2f", nome, categoria, valor);
    }
    
    @Override
    public boolean processar() {
        
        System.out.println("Processando produto: " + nome);
        return true;
    }
    
    // Métodos adicionais para compatibilidade com ProductCell
    public String getName() {
        return getNome();
    }
    
    public double getPrice() {
        return getValor();
    }
}
