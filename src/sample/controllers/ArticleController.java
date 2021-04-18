package sample.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.entities.Article;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class ArticleController implements Initializable {

    @FXML
    public TableView<Article> articles;
    @FXML
    public TableColumn<Article, String> image;
    @FXML
    public TableColumn<Article, String> name;
    @FXML
    public TableColumn<Article, String> categorie;
    @FXML
    public TableColumn<Article, String> promotion;
    @FXML
    private TableColumn<Article, Integer> prix;
    @FXML
    public ChoiceBox<String> categorieChoice;

    public final ObservableList<Article> data = FXCollections.observableArrayList();

    public final ObservableList<String> categorieList = FXCollections.observableArrayList("Tous", "Technologie", "Vetement", "Restaurant");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        categorieChoice.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> filterCategorie(newValue));

        initializeCellValue();
        categorieChoice.setValue("Tous");
        categorieChoice.setItems(categorieList);
        initializeArticles(data);
        articles.setItems(data);
        initializeTableListener();
    }

    public void filterCategorie(String categorie) {
        if (categorie.equals("Tous")) {
            articles.setItems(data);
        } else {

            System.out.println("Filtering using " + categorie);
            List<Article> filteredList = data.stream().filter(article -> article.getCategorie().equals(categorie)).collect(Collectors.toList());
            ObservableList<Article> filteredData = FXCollections.observableArrayList(filteredList);
            articles.setItems(filteredData);
        }
    }

    public void initializeCellValue() {
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        promotion.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

    }

    public void initializeTableListener() {
        articles.setOnMousePressed(e -> {
            if (e.getClickCount() == 2 && e.isPrimaryButtonDown()) {
                Article selectedArticle = articles.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/detailsArticle.fxml"));
                Scene scene = ((Node) e.getSource()).getScene();
                Stage stage = (Stage) scene.getWindow();

                try {
                    stage.setScene(new Scene(loader.load()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                DetailController controller = loader.getController();
                controller.initData(selectedArticle);
                loader.setController(controller);
                stage.show();
            }
        });
    }

    public void initializeArticles(ObservableList<Article> data) {
        Article article1 = new Article("Casque Bluetooth JBL à réduction de bruit", "Technologie", "-40 %", "/images/casque.png", 180, 300);
        Article article2 = new Article("Robe chemise", "Vetement", "-30 %", "/images/robe.png", 30, 49);
        Article article3 = new Article("Plat Crispy Chicken - Hobo", "Restaurant", "-10 %", "/images/hobo.jpg", 18, 20);
        Article article4 = new Article("MacBook Pro - 16 pouces", "Technologie", "-30 %", "/images/mac.png", 3465, 4950);
        data.add(article1);
        data.add(article2);
        data.add(article3);
        data.add(article4);
    }

    public void detailArticle() throws IOException {

    }
}
