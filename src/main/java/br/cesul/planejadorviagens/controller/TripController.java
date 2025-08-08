package br.cesul.planejadorviagens.controller;

import br.cesul.planejadorviagens.model.Viagem;
import br.cesul.planejadorviagens.services.PlanejamentoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TripController {
    @FXML private DatePicker dataInicioPicker;
    @FXML private DatePicker dataFimPicker;
    @FXML private TextField destinoField;
    @FXML private Text orcamentoField;
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
        colIni.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getDataInicio().format(fmt))
                );
        colFim.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getDataFim().format(fmt))
                );
        colCusto.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getCusto())
                );
        viagensTable.setItems(FXCollections.observableArrayList(service.listar()));
        atualizarTotal();
    }

    @FXML
    public void adicionar(){
        try{
            double custo = Double.parseDouble(orcamentoField.getWholeText().replace(",", "."));
        }catch (Exception ex){
            mostrarErro(ex.getMessage());
        }
    }

    private void mostrarErro(String msg){
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    @FXML private void atualizarTotal(){
        lblTotal.setText("Total: R$" + String.format("%.2f" ,service.totalGasto()));
    }
}
