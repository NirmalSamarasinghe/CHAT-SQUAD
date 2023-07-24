package lk.ijse.chat_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/loadingWindowForm.fxml"))));
        Image icon = new Image(getClass().getResourceAsStream("/view/img/logo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("CHAT SQUAD");
        stage.show();
    }
}
