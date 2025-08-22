package br.cesul.planejadorviagens.controller;

import br.cesul.planejadorviagens.model.Viagem;
import br.cesul.planejadorviagens.services.PlanejamentoService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TripController {
    @FXML private DatePicker dataInicioPicker;
    @FXML private DatePicker dataFimPicker;
    @FXML private TextField destinoField;
    @FXML private TextField orcamentoField;
    @FXML private Button btnAdicionar;
    @FXML private TableView<Viagem> viagensTable;
    @FXML private TableColumn<Viagem, String> colCidade;
    @FXML private TableColumn<Viagem, String> colIni;
    @FXML private TableColumn<Viagem, String> colFim;
    @FXML private TableColumn<Viagem, Number> colCusto;
    @FXML private Label lblTotal;

    private final PlanejamentoService service = new PlanejamentoService();

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML private void initialize(){
        colCidade.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getDestino())
                );
        colIni.setCellValueFactory(c -> {
            LocalDate inicio = c.getValue().getDataInicio();
            String data = (inicio != null) ? inicio.format(fmt) : "";
            return new javafx.beans.property.SimpleStringProperty(data);
        });

        colFim.setCellValueFactory(c -> {
            LocalDate fim = c.getValue().getDataFim();
            String data = (fim != null) ? fim.format(fmt) : "";
            return new javafx.beans.property.SimpleStringProperty(data);
        });
        colCusto.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getCusto())
                );

        viagensTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if(newV != null){
                destinoField.setText(newV.getDestino());
                orcamentoField.setText(String.valueOf(newV.getCusto()));
                dataInicioPicker.setValue(newV.getDataInicio());
                dataFimPicker.setValue(newV.getDataFim());
            }
        });

        viagensTable.setItems(FXCollections.observableArrayList(service.listar()));
        atualizarTotal();
    }

    @FXML
    public void adicionar(){
        try{
            String texto = orcamentoField.getText().trim();
            if (texto.isEmpty()) {
                throw new IllegalArgumentException("Digite um valor para o orçamento!");
            }

            //texto = texto.replace(",", "."); // troca vírgula por ponto
            double custo = Double.parseDouble(texto);
            service.adicionar(destinoField.getText(), dataInicioPicker.getValue(), dataFimPicker.getValue(), custo);
            viagensTable.getItems().setAll(service.listar());
            atualizarTotal();
            limparCampos();
        }catch (Exception ex){
            mostrarErro(ex.getMessage());
        }
    }
    @FXML
    public void editar(){
        try {
            String texto = orcamentoField.getText().trim();
            if (texto.isEmpty()) {
                throw new IllegalArgumentException("Digite um valor para o orçamento!");
            }
            double custo = Double.parseDouble(texto);
            Viagem viagemSelecionada = viagensTable.getSelectionModel().getSelectedItem();
            Viagem viagemNova = new Viagem();
            viagemNova.setCusto(custo);
            viagemNova.setDestino(destinoField.getText());
            viagemNova.setDataFim(dataFimPicker.getValue());
            viagemNova.setDataInicio(dataInicioPicker.getValue());
            service.editar(viagemSelecionada, viagemNova);
            viagensTable.getItems().setAll(service.listar());
            atualizarTotal();
            limparCampos();
            viagensTable.getSelectionModel().clearSelection();
        }catch (Exception ex){
            mostrarErro(ex.getMessage());
        }
    }

    @FXML
    public void remover(){
        Viagem v = viagensTable.getSelectionModel().getSelectedItem();
        service.remover(v);
        viagensTable.getItems().setAll(service.listar());
        atualizarTotal();
    }

    private void mostrarErro(String msg){
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    @FXML private void atualizarTotal(){
        lblTotal.setText("Total: R$" + String.format("%.2f" ,service.totalGasto()));
    }

    private void limparCampos(){
        destinoField.clear();
        orcamentoField.clear();
        dataInicioPicker.setValue(null);
        dataFimPicker.setValue(null);
    }
}
