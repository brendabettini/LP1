package org.brenda;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        // Criando abas
        Tab tabBarco = new Tab("BarcoDobradura", formBarco());
        Tab tabJornal = new Tab("Jornal", formJornal());
        Tab tabCanal = new Tab("Canal", formCanal());
        Tab tabRetrato = new Tab("Retrato", formRetrato());
        Tab tabGrao = new Tab("GraoCafe", formGrao());
        Tab tabXicara = new Tab("XicaraCafe", formXicara());

        tabBarco.setClosable(false);
        tabJornal.setClosable(false);
        tabCanal.setClosable(false);
        tabRetrato.setClosable(false);
        tabGrao.setClosable(false);
        tabXicara.setClosable(false);

        tabPane.getTabs().addAll(tabBarco, tabJornal, tabCanal, tabRetrato, tabGrao, tabXicara);

        Scene scene = new Scene(tabPane, 500, 400);
        primaryStage.setTitle("Cadastro Lampedusa & John Lennon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ---------- Formulários ----------

    private GridPane formBarco() {
        GridPane grid = criarGrid();
        TextField cor = new TextField();
        TextField material = new TextField();
        CheckBox dobrado = new CheckBox("Dobrado");

        Button salvar = new Button("Salvar Barco");
        salvar.setOnAction(e -> salvarCSV("barco.csv", cor.getText(), material.getText(), String.valueOf(dobrado.isSelected())));

        grid.addRow(0, new Label("Cor:"), cor);
        grid.addRow(1, new Label("Material:"), material);
        grid.addRow(2, new Label("Dobrado:"), dobrado);
        grid.add(salvar, 1, 3);
        return grid;
    }

    private GridPane formJornal() {
        GridPane grid = criarGrid();
        TextField veiculo = new TextField();
        TextField data = new TextField();
        TextField paginas = new TextField();

        Button salvar = new Button("Salvar Jornal");
        salvar.setOnAction(e -> {
            try {
                new SimpleDateFormat("dd/MM/yyyy").parse(data.getText()); // validação da data
                salvarCSV("jornal.csv", veiculo.getText(), data.getText(), paginas.getText());
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Data inválida (use dd/MM/yyyy)").showAndWait();
            }
        });

        grid.addRow(0, new Label("Veículo:"), veiculo);
        grid.addRow(1, new Label("Data:"), data);
        grid.addRow(2, new Label("Páginas:"), paginas);
        grid.add(salvar, 1, 3);
        return grid;
    }

    private GridPane formCanal() {
        GridPane grid = criarGrid();
        TextField localizacao = new TextField();
        TextField profundidade = new TextField();
        TextField largura = new TextField();

        Button salvar = new Button("Salvar Canal");
        salvar.setOnAction(e -> salvarCSV("canal.csv", localizacao.getText(), profundidade.getText(), largura.getText()));

        grid.addRow(0, new Label("Localização:"), localizacao);
        grid.addRow(1, new Label("Profundidade:"), profundidade);
        grid.addRow(2, new Label("Largura:"), largura);
        grid.add(salvar, 1, 3);
        return grid;
    }

    private GridPane formRetrato() {
        GridPane grid = criarGrid();
        TextField tema = new TextField();
        TextField material = new TextField();
        TextField dimensoes = new TextField();

        Button salvar = new Button("Salvar Retrato");
        salvar.setOnAction(e -> salvarCSV("retrato.csv", tema.getText(), material.getText(), dimensoes.getText()));

        grid.addRow(0, new Label("Tema:"), tema);
        grid.addRow(1, new Label("Material:"), material);
        grid.addRow(2, new Label("Dimensões:"), dimensoes);
        grid.add(salvar, 1, 3);
        return grid;
    }

    private GridPane formGrao() {
        GridPane grid = criarGrid();
        TextField origem = new TextField();
        TextField torra = new TextField();
        TextField quantidade = new TextField();

        Button salvar = new Button("Salvar Grão");
        salvar.setOnAction(e -> salvarCSV("grao.csv", origem.getText(), torra.getText(), quantidade.getText()));

        grid.addRow(0, new Label("Origem:"), origem);
        grid.addRow(1, new Label("Torra:"), torra);
        grid.addRow(2, new Label("Quantidade:"), quantidade);
        grid.add(salvar, 1, 3);
        return grid;
    }

    private GridPane formXicara() {
        GridPane grid = criarGrid();
        TextField cor = new TextField();
        TextField capacidade = new TextField();
        TextField conteudo = new TextField();

        Button salvar = new Button("Salvar Xícara");
        salvar.setOnAction(e -> salvarCSV("xicara.csv", cor.getText(), capacidade.getText(), conteudo.getText()));

        grid.addRow(0, new Label("Cor:"), cor);
        grid.addRow(1, new Label("Capacidade:"), capacidade);
        grid.addRow(2, new Label("Conteúdo:"), conteudo);
        grid.add(salvar, 1, 3);
        return grid;
    }

    // ---------- Utilitários ----------

    private GridPane criarGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        return grid;
    }

    private void salvarCSV(String arquivo, String... valores) {
        try (FileWriter fw = new FileWriter(arquivo, true)) {
            fw.write(String.join(",", valores) + "\n");
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao salvar " + arquivo).showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
