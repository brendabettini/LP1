package org.brenda;

import java.io.FileWriter;
import java.io.IOException;

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

        // Abas
        Tab tabHamburguer = new Tab("Hamburguer", formHamburguer());
        Tab tabBatata = new Tab("BatataFrita", formBatata());
        Tab tabRefrigerante = new Tab("Refrigerante", formRefrigerante());

        tabHamburguer.setClosable(false);
        tabBatata.setClosable(false);
        tabRefrigerante.setClosable(false);

        tabPane.getTabs().addAll(tabHamburguer, tabBatata, tabRefrigerante);

        Scene scene = new Scene(tabPane, 500, 400);
        primaryStage.setTitle("Cadastro Doodle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // -------- Formularios --------

    private GridPane formHamburguer() {
        GridPane grid = criarGrid();
        TextField tamanho = new TextField();
        TextField ingredientes = new TextField();
        TextField calorias = new TextField();

        Button salvar = new Button("Salvar Hamburguer");
        salvar.setOnAction(e -> salvarCSV("hamburguer.csv", tamanho.getText(), ingredientes.getText(), calorias.getText()));

        grid.addRow(0, new Label("Tamanho:"), tamanho);
        grid.addRow(1, new Label("Ingredientes:"), ingredientes);
        grid.addRow(2, new Label("Calorias:"), calorias);
        grid.add(salvar, 1, 3);
        return grid;
    }

    private GridPane formBatata() {
        GridPane grid = criarGrid();
        TextField quantidade = new TextField();
        TextField temperatura = new TextField();
        CheckBox crocancia = new CheckBox("Crocante");

        Button salvar = new Button("Salvar Batata");
        salvar.setOnAction(e -> salvarCSV("batata.csv", quantidade.getText(), temperatura.getText(), String.valueOf(crocancia.isSelected())));

        grid.addRow(0, new Label("Quantidade:"), quantidade);
        grid.addRow(1, new Label("Temperatura:"), temperatura);
        grid.addRow(2, new Label("Crocante:"), crocancia);
        grid.add(salvar, 1, 3);
        return grid;
    }

    private GridPane formRefrigerante() {
        GridPane grid = criarGrid();
        TextField sabor = new TextField();
        TextField volume = new TextField();
        CheckBox gelo = new CheckBox("Com gelo");

        Button salvar = new Button("Salvar Refrigerante");
        salvar.setOnAction(e -> salvarCSV("refrigerante.csv", sabor.getText(), volume.getText(), String.valueOf(gelo.isSelected())));

        grid.addRow(0, new Label("Sabor:"), sabor);
        grid.addRow(1, new Label("Volume:"), volume);
        grid.addRow(2, new Label("Gelo:"), gelo);
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