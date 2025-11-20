package org.LP2;

import org.LP2.DAO.AlmaDAO;
import org.LP2.DAO.OlhosDAO;
import org.LP2.DAO.PortaDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML private TextField portaCorField;
    @FXML private TextField portaEstadoField;
    @FXML private TextField portaDestinoField;

    @FXML private TableView<Porta> portaTable;
    @FXML private TableColumn<Porta, Integer> portaIdCol;
    @FXML private TableColumn<Porta, String> portaCorCol;
    @FXML private TableColumn<Porta, String> portaEstadoCol;
    @FXML private TableColumn<Porta, String> portaDestinoCol;

    private final PortaDAO portaDAO = new PortaDAO();
    private final ObservableList<Porta> portas = FXCollections.observableArrayList();
    private Porta portaSelecionada;


    @FXML private TextField olhosCorField;
    @FXML private CheckBox olhosAbertoCheck;
    @FXML private TextField olhosNitidezField;

    @FXML private TableView<Olhos> olhosTable;
    @FXML private TableColumn<Olhos, Integer> olhosIdCol;
    @FXML private TableColumn<Olhos, String> olhosCorCol;
    @FXML private TableColumn<Olhos, Boolean> olhosAbertoCol;
    @FXML private TableColumn<Olhos, Integer> olhosNitidezCol;

    private final OlhosDAO olhosDAO = new OlhosDAO();
    private final ObservableList<Olhos> olhosLista = FXCollections.observableArrayList();
    private Olhos olhosSelecionado;

    @FXML private TextField almaEstadoField;
    @FXML private TextField almaTemperaturaField;
    @FXML private TextField almaConexaoField;

    @FXML private TableView<Alma> almaTable;
    @FXML private TableColumn<Alma, Integer> almaIdCol;
    @FXML private TableColumn<Alma, String> almaEstadoCol;
    @FXML private TableColumn<Alma, Integer> almaTemperaturaCol;
    @FXML private TableColumn<Alma, Integer> almaConexaoCol;

    private final AlmaDAO almaDAO = new AlmaDAO();
    private final ObservableList<Alma> almas = FXCollections.observableArrayList();
    private Alma almaSelecionada;

    public MainController() {
    }

    @FXML
    public void initialize() {
        initTabelaPorta();
        initTabelaOlhos();
        initTabelaAlma();
    }


    private void initTabelaPorta() {
        portaIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        portaCorCol.setCellValueFactory(new PropertyValueFactory<>("cor"));
        portaEstadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
        portaDestinoCol.setCellValueFactory(new PropertyValueFactory<>("destino"));

        portaTable.setItems(portas);
        portas.setAll(portaDAO.listarTodos());

        portaTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> {
                    portaSelecionada = newSel;
                    preencherFormPorta(newSel);
                });
    }

    private void preencherFormPorta(Porta p) {
        if (p == null) {
            portaCorField.clear();
            portaEstadoField.clear();
            portaDestinoField.clear();
        } else {
            portaCorField.setText(p.getCor());
            portaEstadoField.setText(p.getEstado());
            portaDestinoField.setText(p.getDestino());
        }
    }

    @FXML
    private void onNovaPorta() {
        portaSelecionada = null;
        preencherFormPorta(null);
    }

    @FXML
    private void onSalvarPorta() {
        String cor = portaCorField.getText();
        String estado = portaEstadoField.getText();
        String destino = portaDestinoField.getText();

        if (cor.isBlank() || estado.isBlank() || destino.isBlank()) {
            showAlert("Preencha todos os campos de Porta.");
            return;
        }

        if (portaSelecionada == null) {
            portaSelecionada = new Porta(null, cor, estado, destino);
        } else {
            portaSelecionada.setCor(cor);
            portaSelecionada.setEstado(estado);
            portaSelecionada.setDestino(destino);
        }

        portaDAO.salvar(portaSelecionada);
        portas.setAll(portaDAO.listarTodos());
        preencherFormPorta(null);
        portaSelecionada = null;
    }

    @FXML
    private void onExcluirPorta() {
        if (portaSelecionada == null) {
            showAlert("Selecione uma Porta para excluir.");
            return;
        }
        portaDAO.deletar(portaSelecionada);
        portas.setAll(portaDAO.listarTodos());
        preencherFormPorta(null);
        portaSelecionada = null;
    }


    private void initTabelaOlhos() {
        olhosIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        olhosCorCol.setCellValueFactory(new PropertyValueFactory<>("cor"));
        olhosAbertoCol.setCellValueFactory(new PropertyValueFactory<>("aberto"));
        olhosNitidezCol.setCellValueFactory(new PropertyValueFactory<>("nitidez"));

        olhosTable.setItems(olhosLista);
        olhosLista.setAll(olhosDAO.listarTodos());

        olhosTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> {
                    olhosSelecionado = newSel;
                    preencherFormOlhos(newSel);
                });
    }

    private void preencherFormOlhos(Olhos o) {
        if (o == null) {
            olhosCorField.clear();
            olhosAbertoCheck.setSelected(false);
            olhosNitidezField.clear();
        } else {
            olhosCorField.setText(o.getCor());
            olhosAbertoCheck.setSelected(o.isAberto());
            olhosNitidezField.setText(String.valueOf(o.getNitidez()));
        }
    }

    @FXML
    private void onNovosOlhos() {
        olhosSelecionado = null;
        preencherFormOlhos(null);
    }

    @FXML
    private void onSalvarOlhos() {
        String cor = olhosCorField.getText();
        String nitidezStr = olhosNitidezField.getText();

        if (cor.isBlank() || nitidezStr.isBlank()) {
            showAlert("Preencha cor e nitidez dos Olhos.");
            return;
        }

        int nitidez;
        try {
            nitidez = Integer.parseInt(nitidezStr);
        } catch (NumberFormatException e) {
            showAlert("Nitidez deve ser número inteiro.");
            return;
        }

        boolean aberto = olhosAbertoCheck.isSelected();

        if (olhosSelecionado == null) {
            olhosSelecionado = new Olhos(null, cor, aberto, nitidez);
        } else {
            olhosSelecionado.setCor(cor);
            olhosSelecionado.setAberto(aberto);
            olhosSelecionado.setNitidez(nitidez);
        }

        olhosDAO.salvar(olhosSelecionado);
        olhosLista.setAll(olhosDAO.listarTodos());
        preencherFormOlhos(null);
        olhosSelecionado = null;
    }

    @FXML
    private void onExcluirOlhos() {
        if (olhosSelecionado == null) {
            showAlert("Selecione um registro de Olhos para excluir.");
            return;
        }
        olhosDAO.deletar(olhosSelecionado);
        olhosLista.setAll(olhosDAO.listarTodos());
        preencherFormOlhos(null);
        olhosSelecionado = null;
    }

    private void initTabelaAlma() {
        almaIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        almaEstadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
        almaTemperaturaCol.setCellValueFactory(new PropertyValueFactory<>("temperatura"));
        almaConexaoCol.setCellValueFactory(new PropertyValueFactory<>("conexao"));

        almaTable.setItems(almas);
        almas.setAll(almaDAO.listarTodos());

        almaTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> {
                    almaSelecionada = newSel;
                    preencherFormAlma(newSel);
                });
    }

    private void preencherFormAlma(Alma a) {
        if (a == null) {
            almaEstadoField.clear();
            almaTemperaturaField.clear();
            almaConexaoField.clear();
        } else {
            almaEstadoField.setText(a.getEstado());
            almaTemperaturaField.setText(String.valueOf(a.getTemperatura()));
            almaConexaoField.setText(String.valueOf(a.getConexao()));
        }
    }

    @FXML
    private void onNovaAlma() {
        almaSelecionada = null;
        preencherFormAlma(null);
    }

    @FXML
    private void onSalvarAlma() {
        String estado = almaEstadoField.getText();
        String tempStr = almaTemperaturaField.getText();
        String conexaoStr = almaConexaoField.getText();

        if (estado.isBlank() || tempStr.isBlank() || conexaoStr.isBlank()) {
            showAlert("Preencha todos os campos da Alma.");
            return;
        }

        int temperatura;
        int conexao;
        try {
            temperatura = Integer.parseInt(tempStr);
            conexao = Integer.parseInt(conexaoStr);
        } catch (NumberFormatException e) {
            showAlert("Temperatura e Conexão devem ser inteiros.");
            return;
        }

        if (almaSelecionada == null) {
            almaSelecionada = new Alma(null, estado, temperatura, conexao);
        } else {
            almaSelecionada.setEstado(estado);
            almaSelecionada.setTemperatura(temperatura);
            almaSelecionada.setConexao(conexao);
        }

        almaDAO.salvar(almaSelecionada);
        almas.setAll(almaDAO.listarTodos());
        preencherFormAlma(null);
        almaSelecionada = null;
    }

    @FXML
    private void onExcluirAlma() {
        if (almaSelecionada == null) {
            showAlert("Selecione uma Alma para excluir.");
            return;
        }
        almaDAO.deletar(almaSelecionada);
        almas.setAll(almaDAO.listarTodos());
        preencherFormAlma(null);
        almaSelecionada = null;
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
