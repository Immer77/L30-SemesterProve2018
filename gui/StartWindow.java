package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Arrangement;
import model.Tutor;
import storage.Storage;

import java.util.concurrent.Callable;

public class StartWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Administration af tutorer og arrangementer");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    public void init(){
        Controller.init();
    }

    private TextField txfName,txfEmail;
    private TextArea txaArrangementer;
    private ListView<Tutor> lvwTutorer;
    private ListView<Arrangement> lvwArrangementer;

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setHgap(10);
        pane.setHgap(10);

        Label lblTutorer = new Label("Tutorer");
        pane.add(lblTutorer,0,0);

        lvwTutorer = new ListView<>();
        pane.add(lvwTutorer,0,1,1,1);
        lvwTutorer.setPrefHeight(200);
        lvwTutorer.setPrefWidth(200);
        lvwTutorer.getItems().setAll(Controller.getTutors());
        lvwTutorer.setOnMouseClicked(event -> this.updateMethodTutors());
        ChangeListener<Tutor> listener = (ov, oldTutor, newTutor) -> this.selectedTutorsChanged();
        lvwTutorer.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblTutorName = new Label("Navn");
        pane.add(lblTutorName,1,0);
        txfName = new TextField();
        pane.add(txfName,2,0);

        Label lblEmail = new Label("Email:");
        pane.add(lblEmail,1,1);
        txfEmail = new TextField();
        pane.add(txfEmail,2,1);

        Label lblArrangementer = new Label("Arrangementer");
        pane.add(lblArrangementer,1,2);
        txaArrangementer = new TextArea();
        txaArrangementer.setPrefHeight(150);
        txaArrangementer.setPrefWidth(150);
        pane.add(txaArrangementer,2,2);

        Button btnFjern = new Button("Fjern");
        pane.add(btnFjern,2,4);
        btnFjern.setOnAction(event -> this.removeTutor());

        Label lblAlleMuligArrangementer = new Label("Alle mulige arrangementer");
        pane.add(lblAlleMuligArrangementer,3,1);
        lvwArrangementer = new ListView<>();
        lvwArrangementer.getItems().setAll(Controller.getArrangementer());
        lvwArrangementer.setPrefWidth(150);
        lvwArrangementer.setPrefHeight(150);
        pane.add(lvwArrangementer,3,2);

        Button btnTilføj = new Button("Tilføj");
        pane.add(btnTilføj,3,4);
        btnTilføj.setOnAction(event -> this.addArrangementToTutor());


    }

    private void addArrangementToTutor() {
        Controller.addTutorToArrangement(lvwTutorer.getSelectionModel().getSelectedItem(),lvwArrangementer.getSelectionModel().getSelectedItem());
        updateControls();
    }

    private void removeTutor() {
        if(txfName.getText() != null && txfEmail != null){
            Controller.removeTutor(lvwTutorer.getSelectionModel().getSelectedItem());
            txfName.clear();
            txfEmail.clear();
            txaArrangementer.clear();
            lvwTutorer.getItems().setAll(Controller.getTutors());
        }

    }

    private void selectedTutorsChanged() {
        updateControls();
    }

    private void updateMethodTutors() {
        this.updateControls();
    }

    public void updateControls() {
        Tutor tutor = lvwTutorer.getSelectionModel().getSelectedItem();
        if (tutor != null) {
            txfName.setText(tutor.getNavn());
            txfEmail.setText("" + tutor.getEmail());
            StringBuilder sb = new StringBuilder();
            for (Arrangement a : tutor.getArrangementer()) {
                sb.append(a + "\n");
            }
            txaArrangementer.setText(sb.toString());
        } else {
            txfName.clear();
            txfEmail.clear();
            txaArrangementer.clear();
        }
    }
}
