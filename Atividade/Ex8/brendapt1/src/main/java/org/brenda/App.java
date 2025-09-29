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

        // Porta
        Tab tabPorta = new Tab("Porta", criarFormularioPorta());
        tabPorta.setClosable(false);

        // Olhos
        Tab tabOlhos = new Tab("Olhos", criarFormularioOlhos());
        tabOlhos.setClosable(false);

        // Alma
        Tab tabAlma = new Tab("Alma", criarFormularioAlma());
        tabAlma.setClosable(false);

        tabPane.getTabs().addAll(tabPorta, tabOlhos, tabAlma);

        Scene scene = new Scene(tabPane, 400, 300);
        primaryStage.setTitle("Cadastro Porta, Olhos e Alma");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane criarFormularioPorta() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField corField = new TextField();
        TextField estadoField = new TextField();
        TextField destinoField = new TextField();

        Button salvarBtn = new Button("Salvar Porta");
        salvarBtn.setOnAction(e -> {
            try {
                Porta porta = new Porta(corField.getText(), estadoField.getText(), destinoField.getText());
                salvarCSV("porta.csv", corField.getText(), estadoField.getText(), destinoField.getText());
                corField.clear(); estadoField.clear(); destinoField.clear();
                new Alert(Alert.AlertType.INFORMATION, "Porta salva!").showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro ao salvar Porta").showAndWait();
            }
        });

        grid.add(new Label("Cor:"), 0, 0);
        grid.add(corField, 1, 0);
        grid.add(new Label("Estado:"), 0, 1);
        grid.add(estadoField, 1, 1);
        grid.add(new Label("Destino:"), 0, 2);
        grid.add(destinoField, 1, 2);
        grid.add(salvarBtn, 1, 3);

        return grid;
    }

    private GridPane criarFormularioOlhos() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField corField = new TextField();
        CheckBox abertoCheck = new CheckBox("Aberto");
        TextField nitidezField = new TextField();

        Button salvarBtn = new Button("Salvar Olhos");
        salvarBtn.setOnAction(e -> {
            try {
                Olhos olhos = new Olhos(corField.getText(), abertoCheck.isSelected(), Integer.parseInt(nitidezField.getText()));
                salvarCSV("olhos.csv", corField.getText(), String.valueOf(abertoCheck.isSelected()), nitidezField.getText());
                corField.clear(); nitidezField.clear(); abertoCheck.setSelected(false);
                new Alert(Alert.AlertType.INFORMATION, "Olhos salvos!").showAndWait();
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Nitidez deve ser número!").showAndWait();
            }
        });

        grid.add(new Label("Cor:"), 0, 0);
        grid.add(corField, 1, 0);
        grid.add(new Label("Aberto:"), 0, 1);
        grid.add(abertoCheck, 1, 1);
        grid.add(new Label("Nitidez:"), 0, 2);
        grid.add(nitidezField, 1, 2);
        grid.add(salvarBtn, 1, 3);

        return grid;
    }

    private GridPane criarFormularioAlma() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField estadoField = new TextField();
        TextField temperaturaField = new TextField();
        TextField conexaoField = new TextField();

        Button salvarBtn = new Button("Salvar Alma");
        salvarBtn.setOnAction(e -> {
            try {
                Alma alma = new Alma(estadoField.getText(), Integer.parseInt(temperaturaField.getText()), Integer.parseInt(conexaoField.getText()));
                salvarCSV("alma.csv", estadoField.getText(), temperaturaField.getText(), conexaoField.getText());
                estadoField.clear(); temperaturaField.clear(); conexaoField.clear();
                new Alert(Alert.AlertType.INFORMATION, "Alma salva!").showAndWait();
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Temperatura e Conexão devem ser números!").showAndWait();
            }
        });

        grid.add(new Label("Estado:"), 0, 0);
        grid.add(estadoField, 1, 0);
        grid.add(new Label("Temperatura:"), 0, 1);
        grid.add(temperaturaField, 1, 1);
        grid.add(new Label("Conexão:"), 0, 2);
        grid.add(conexaoField, 1, 2);
        grid.add(salvarBtn, 1, 3);

        return grid;
    }

    private void salvarCSV(String arquivo, String... valores) {
        try (FileWriter fw = new FileWriter(arquivo, true)) {
            fw.write(String.join(",", valores) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
