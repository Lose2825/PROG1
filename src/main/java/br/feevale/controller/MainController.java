package br.feevale.controller;

import br.feevale.model.ItemAtendimento;
import br.feevale.model.Produto;
import br.feevale.model.PedidoRestaurante;
import br.feevale.service.GerenciadorAtendimentos;
import br.feevale.view.ItemCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Controller da interface principal do cliente.
 * Gerencia a seleção de produtos e criação de pedidos.
 */
public class MainController {

    @FXML private ListView<ItemAtendimento> productList;
    @FXML private ListView<ItemAtendimento> cartList;
    @FXML private Label totalLabel;
    @FXML private Label statusLabel;

    private final ObservableList<ItemAtendimento> cart = FXCollections.observableArrayList();
    private PedidoRestaurante pedidoAtual;
    private final GerenciadorAtendimentos gerenciador = GerenciadorAtendimentos.getInstancia();


    @FXML
    public void initialize() {
        // Usa ItemCell para exibir itens (polimorfismo - funciona com Produto e Servico)
        productList.setCellFactory(lv -> new ItemCell());
        cartList.setCellFactory(lv -> new ItemCell());

        // Menu de exemplo usando a nova classe Produto
        ObservableList<ItemAtendimento> menu = FXCollections.observableArrayList(
            new Produto("Hambúrguer", 18.50, "/br/feevale/img/hamburguer.png", "Lanche"),
            new Produto("Batata Frita", 9.00, "/br/feevale/img/batata.png", "Acompanhamento"),
            new Produto("Refrigerante", 6.50, "/br/feevale/img/refri.png", "Bebida"),
            new Produto("Sorvete", 8.00, "/br/feevale/img/refri.png", "Sobremesa")
        );

        productList.setItems(menu);
        cartList.setItems(cart);
        
        // Inicializa um novo pedido
        iniciarNovoPedido();
        
        updateTotal();
    }
    
    /**
     * Inicia um novo pedido com número único.
     */
    private void iniciarNovoPedido() {
        int numeroAtendimento = gerenciador.gerarNumeroAtendimento();
        pedidoAtual = new PedidoRestaurante(numeroAtendimento);
        if (statusLabel != null) {
            statusLabel.setText("Pedido #" + numeroAtendimento);
        }
    }


    @FXML
    public void addItem() {
        ItemAtendimento sel = productList.getSelectionModel().getSelectedItem();
        if (sel != null) {
            pedidoAtual.addItem(sel);
            cart.add(sel);
            updateTotal();
        }
    }


    @FXML
    public void removeItem() {
        ItemAtendimento sel = cartList.getSelectionModel().getSelectedItem();
        if (sel != null) {
            pedidoAtual.removeItem(sel);
            cart.remove(sel);
            updateTotal();
        }
    }


    @FXML
    public void confirm() {
        if (pedidoAtual.getItens().isEmpty()) {
            showInfo("Carrinho vazio", "Adicione itens antes de confirmar.");
            return;
        }
        
        // Adiciona o pedido ao gerenciador
        gerenciador.adicionarAtendimento(pedidoAtual);
        
        // Mostra o resumo do pedido
        showInfo("Pedido confirmado!", pedidoAtual.getResumo());
        
        // Limpa o carrinho e inicia novo pedido
        cart.clear();
        iniciarNovoPedido();
        updateTotal();
    }


    @FXML
    public void abrirGerenciamento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/manager.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) totalLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
            showInfo("Erro", "Não foi possível abrir o gerenciamento.");
        }
    }

    private void updateTotal() {
        double total = pedidoAtual.getTotal();
        totalLabel.setText(String.format("Total: R$ %.2f", total));
    }

    private void showInfo(String title, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }
}
