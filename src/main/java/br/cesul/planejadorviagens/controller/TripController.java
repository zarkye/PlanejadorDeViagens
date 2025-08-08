package br.cesul.planejadorviagens.controller;

import br.cesul.planejadorviagens.model.Viagem;
import br.cesul.planejadorviagens.services.PlanejamentoService;
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
    @FXML private TableColumn<Viagem, String> colCidade;
    @FXML private TableColumn<Viagem, String> colIni;
    @FXML private TableColumn<Viagem, String> colFim;
    @FXML private TableColumn<Viagem, Number> colCusto;
    @FXML private Label lblTotal;
    private final PlanejamentoService service = new PlanejamentoService();
    @FXML private void initialize(){
        
    }
}
