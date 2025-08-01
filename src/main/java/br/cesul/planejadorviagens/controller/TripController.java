package br.cesul.planejadorviagens.controller;

import br.cesul.planejadorviagens.model.Viagem;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.util.Date;

public class TripController {
    @FXML private DatePicker dataInicioPicker;
    @FXML private DatePicker dataFimPicker;
    @FXML private TextField destinoField;
    @FXML private Text orcamentoField;
    @FXML private Button btnAdicionar;
    @FXML private TableView<Viagem> viagensTable;
    @FXML private TableColumn<> colCidade;
    @FXML private TableColumn<> colIni;
    @FXML private TableColumn<> colFim;
    @FXML private TableColumn<> colCusto;
    @FXML private Label lblTotal;
}
