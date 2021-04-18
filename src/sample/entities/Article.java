package sample.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Article {
    private SimpleStringProperty name;
    private SimpleStringProperty categorie;
    private SimpleStringProperty promotion;
    private ImageView image;
    private SimpleIntegerProperty prix;
    public String imagePath;
    public int original_prix = 50;
    public String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    public Article(String name, String categorie, String promotion, String imagePath, int prix, int original_prix) {
        this.name = new SimpleStringProperty(name);
        this.categorie = new SimpleStringProperty(categorie);
        this.promotion = new SimpleStringProperty(promotion);
        this.prix = new SimpleIntegerProperty(prix);
        this.image = new ImageView(new Image(this.getClass().getResourceAsStream(imagePath)));
        this.imagePath = imagePath;
        this.original_prix = original_prix;
        image.setFitHeight(200);
        image.setFitWidth(200);


    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCategorie() {
        return categorie.get();
    }

    public SimpleStringProperty categorieProperty() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie.set(categorie);
    }

    public String getPromotion() {
        return promotion.get();
    }

    public SimpleStringProperty promotionProperty() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion.set(promotion);
    }


    public int getPrix() {
        return prix.get();
    }

    public SimpleIntegerProperty prixProperty() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix.set(prix);
    }
}
