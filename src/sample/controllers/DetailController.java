package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.entities.Article;
import sample.entities.NumberSpinner;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DetailController implements Initializable {
    private Article article;

    @FXML
    private Label titre;

    @FXML
    private ImageView image;

    @FXML
    private TextArea description;

    @FXML
    private Label prix;

    @FXML
    private Label prixOriginal;

    @FXML
    private Spinner<Integer> quantity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private ToggleButton retour;

    @FXML
    void back(ActionEvent event) throws IOException {
        System.out.println("Back clicked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listArticles.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();

        Stage stage = (Stage) scene.getWindow();
        Scene newScene = new Scene(loader.load());
        newScene.getStylesheets().add(this.getClass().getResource("/styles/style.css").toExternalForm());
        stage.setScene(newScene);

        ArticleController controller = loader.getController();
        loader.setController(controller);

        stage.show();


    }

    @FXML
    void acheter(MouseEvent event) {
        System.out.println("Acheter clicked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/paiement.fxml"));
        try {
            Stage stage = new Stage(StageStyle.DECORATED);
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(this.getClass().getResource("/styles/modal.css").toExternalForm());
            stage.setScene(scene);
            AcheterController controller = loader.getController();
            loader.setController(controller);
            controller.initController(this.article.getPrix(), quantity.getValue());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initData(Article article) {
        this.article = article;
        image.setImage(new Image(this.getClass().getResourceAsStream(article.imagePath)));
        image.setFitWidth(600);
        image.setFitHeight(600);
        titre.setText(this.article.getName());
        description.setText(this.article.description);
        prix.setText(article.getPrix() + " DT");
        prixOriginal.setText(article.original_prix + " DT");
        prixOriginal.setStyle("-fx-strikethrough: true");
        quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
    }
}

