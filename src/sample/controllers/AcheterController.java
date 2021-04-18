package sample.controllers;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AcheterController implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField num;

    @FXML
    private ChoiceBox<Integer> month;

    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    private PasswordField cryp;
    private int montant;
    private int quantite;

    @FXML
    void confirmer(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/recu.fxml"));
        try {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(loader.load()));
            RecuController controller = loader.getController();
            loader.setController(controller);
            controller.initController(this.montant * this.quantite, this.quantite, this.nom.getText());

            Animation transition = new Transition() {
                {
                    setCycleDuration(Duration.millis(2000));
                    setInterpolator(Interpolator.EASE_IN);
                }

                @Override
                protected void interpolate(double frac) {
                    stage.setHeight(400 * frac);
                    stage.setWidth(400);

                }
            };
            transition.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Integer> months_number = IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
        List<Integer> years_number = IntStream.rangeClosed(2021, 2030).boxed().collect(Collectors.toList());

        ObservableList<Integer> month_choice = FXCollections.observableArrayList(months_number);
        ObservableList<Integer> years_choice = FXCollections.observableArrayList(years_number);

        num.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    num.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        year.setItems(years_choice);
        month.setItems(month_choice);
    }

    public void initController(int montant, int quantite) {
        this.montant = montant;
        this.quantite = quantite;
    }

}
