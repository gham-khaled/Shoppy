package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class RecuController implements Initializable {
    @FXML
    private Label nom;

    @FXML
    private Label quantity;

    @FXML
    private Label montant;

    @FXML
    private Label date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("In Recu");
    }

    public void initController(int montant, int quantite, String nom) {
        System.out.println(montant);
        this.nom.setText(nom);
        this.montant.setText(montant + " DT");
        this.quantity.setText(quantite + "");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date.setText(dtf.format(now));
    }
}
