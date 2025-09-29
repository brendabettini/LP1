package org.brenda;


import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

        // Abas
        Tab tabBulgogi = new Tab("Bulgogi", formBulgogi());
        Tab tabJapchae = new Tab("Japchae", formJapchae());
        Tab tabKimchi = new Tab("Kimchi", formKimchi());

        tabBulgogi.setClosable(false);
        tabJapchae.setClosable(false);
        tabKimchi.setClosable(false);

        tabPane.getTabs().addAll(tabBulgogi, tabJapchae, tabKimchi);

        Scene scene = new Scene(tabPane, 500, 400);
        primaryStage.setTitle("Cadastro Vídeo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // -------- Formularios --------

    private GridPane formBulgogi() {
        GridPane grid = criarGrid();
        TextField corte = new TextField();
        TextField tempo = new TextField();
        TextField temperatura = new TextField();

        Button salvar = new Button("Salvar Bulgogi");
        salvar.setOnAction(e -> salvarCSV("bulgogi.csv", corte.getText(), tempo.getText(), temperatura.getText()));

        grid.addRow(0, new Label("Corte:"), corte);
        grid.addRow(1, new Label("Tempo de Marinada:"), tempo);
        grid.addRow(2, new Label("Temperatura:"), temperatura);
        grid.add(salvar, 1, 3);
        return grid;
    }

    private GridPane formJapchae() {
        GridPane grid = criarGrid();
        TextField macarrao = new TextField();
        TextField vegetais = new TextField();
        TextField molho = new TextField();

        Button salvar = new Button("Salvar Japchae");
        salvar.setOnAction(e -> salvarCSV("japchae.csv", macarrao.getText(), vegetais.getText(), molho.getText()));

        grid.addRow(0, new Label("Tipo Macarrão:"), macarrao);
        grid.addRow(1, new Label("Vegetais:"), vegetais);
        grid.addRow(2, new Label("Molho:"), molho);
        grid.add(salvar, 1, 3);
        return grid;
    }

    private GridPane formKimchi() {
        GridPane grid = criarGrid();
        TextField tipo = new TextField();
        TextField picancia = new TextField();
        TextField fermentacao = new TextField();

        Button salvar = new Button("Salvar Kimchi");
        salvar.setOnAction(e -> salvarCSV("kimchi.csv", tipo.getText(), picancia.getText(), fermentacao.getText()));

        grid.addRow(0, new Label("Tipo:"), tipo);
        grid.addRow(1, new Label("Picância:"), picancia);
        grid.addRow(2, new Label("Fermentação (dias):"), fermentacao);
        grid.add(salvar, 1, 3);
        return grid;
    }

    // -------- Utilitários --------

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