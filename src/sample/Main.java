package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/listArticles.fxml"));
        primaryStage.setTitle("Shoppy");
        Scene scene = new Scene(root, 1280, 960);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(this.getClass().getResource("/styles/style.css").toExternalForm());


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
