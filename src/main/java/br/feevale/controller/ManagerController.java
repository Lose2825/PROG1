package br.feevale.controller;

import br.feevale.model.Atendimento;
import br.feevale.model.PedidoRestaurante;
import br.feevale.model.StatusPedido;
import br.feevale.service.GerenciadorAtendimentos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controller da interface de gerenciamento.
 * Permite ao gestor visualizar atendimentos, atualizar status e gerar relatórios.
 */
public class ManagerController {

    @FXML private ListView<Atendimento> atendimentosList;
    @FXML private TextArea detalhesArea;
    @FXML private Label totalAtendimentosLabel;
    @FXML private Label valorTotalLabel;
    @FXML private Label valorMedioLabel;
    @FXML private ComboBox<StatusPedido> statusComboBox;
    @FXML private Button atualizarStatusBtn;

    private final GerenciadorAtendimentos gerenciador = GerenciadorAtendimentos.getInstancia();
    private final ObservableList<Atendimento> atendimentos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configura a lista de atendimentos
        atendimentosList.setItems(atendimentos);
        
        // Listener para selecionar um atendimento e exibir detalhes
        atendimentosList.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> exibirDetalhes(newVal)
        );
        
        // Configura o ComboBox de status
        statusComboBox.setItems(FXCollections.observableArrayList(StatusPedido.values()));
        
        // Desabilita botão de atualizar status inicialmente
        if (atualizarStatusBtn != null) {
            atualizarStatusBtn.setDisable(true);
        }
        
        // Carrega os atendimentos
        carregarAtendimentos();
    }

    /**
     * Carrega todos os atendimentos do gerenciador.
     */
    private void carregarAtendimentos() {
        atendimentos.clear();
        atendimentos.addAll(gerenciador.getAtendimentos());
        atualizarEstatisticas();
    }

    /**
     * Exibe os detalhes de um atendimento selecionado.
     */
    private void exibirDetalhes(Atendimento atendimento) {
        if (atendimento == null) {
            detalhesArea.clear();
            if (atualizarStatusBtn != null) {
                atualizarStatusBtn.setDisable(true);
            }
            return;
        }
        
        detalhesArea.setText(atendimento.getResumo());
        
        // Habilita atualização de status apenas para PedidoRestaurante
        if (atendimento instanceof PedidoRestaurante) {
            PedidoRestaurante pedido = (PedidoRestaurante) atendimento;
            statusComboBox.setValue(pedido.getStatus());
            if (atualizarStatusBtn != null) {
                atualizarStatusBtn.setDisable(false);
            }
        } else {
            if (atualizarStatusBtn != null) {
                atualizarStatusBtn.setDisable(true);
            }
        }
    }

    /**
     * Atualiza as estatísticas exibidas.
     */
    private void atualizarEstatisticas() {
        totalAtendimentosLabel.setText(String.valueOf(gerenciador.getTotalAtendimentos()));
        valorTotalLabel.setText(String.format("R$ %.2f", gerenciador.getValorTotalAtendimentos()));
        valorMedioLabel.setText(String.format("R$ %.2f", gerenciador.getValorMedio()));
    }

    /**
     * Atualiza o status do pedido selecionado.
     */
    @FXML
    public void atualizarStatus() {
        Atendimento selecionado = atendimentosList.getSelectionModel().getSelectedItem();
        
        if (selecionado instanceof PedidoRestaurante) {
            PedidoRestaurante pedido = (PedidoRestaurante) selecionado;
            StatusPedido novoStatus = statusComboBox.getValue();
            
            if (novoStatus != null) {
                pedido.setStatus(novoStatus);
                exibirDetalhes(pedido); // Atualiza a visualização
                atendimentosList.refresh(); // Atualiza a lista
                showInfo("Status atualizado", 
                        String.format("Status do pedido #%d atualizado para: %s", 
                                     pedido.getNumeroAtendimento(), novoStatus));
            }
        }
    }

    /**
     * Avança o status do pedido para o próximo na sequência.
     */
    @FXML
    public void avancarStatus() {
        Atendimento selecionado = atendimentosList.getSelectionModel().getSelectedItem();
        
        if (selecionado instanceof PedidoRestaurante) {
            PedidoRestaurante pedido = (PedidoRestaurante) selecionado;
            StatusPedido statusAnterior = pedido.getStatus();
            pedido.avancarStatus();
            
            if (pedido.getStatus() == statusAnterior) {
                showInfo("Aviso", "O pedido já está no status final.");
            } else {
                exibirDetalhes(pedido);
                atendimentosList.refresh();
                showInfo("Status avançado", 
                        String.format("Pedido #%d: %s → %s", 
                                     pedido.getNumeroAtendimento(), 
                                     statusAnterior, pedido.getStatus()));
            }
        }
    }

    /**
     * Gera e exibe o relatório completo.
     */
    @FXML
    public void gerarRelatorio() {
        String relatorio = gerenciador.gerarRelatorio();
        detalhesArea.setText(relatorio);
    }

    /**
     * Atualiza a lista de atendimentos.
     */
    @FXML
    public void atualizar() {
        carregarAtendimentos();
        showInfo("Atualizado", "Lista de atendimentos atualizada com sucesso.");
    }

    /**
     * Volta para a tela do cliente.
     */
    @FXML
    public void voltarParaCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/main.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) atendimentosList.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
            showInfo("Erro", "Não foi possível voltar para a tela do cliente.");
        }
    }

    private void showInfo(String title, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }
}
