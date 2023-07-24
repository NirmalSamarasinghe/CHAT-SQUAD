package lk.ijse.chat_application.controller;
import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingWindowFormController implements Initializable {
    @FXML
    private ImageView imgLogo;
    @FXML
    private ProgressBar progressBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ImageView imageView = new ImageView();

        ScaleTransition zoomIn = new ScaleTransition(Duration.seconds(1.5), imgLogo);
        zoomIn.setFromX(1.0);
        zoomIn.setFromY(1.0);
        zoomIn.setToX(1.5);
        zoomIn.setToY(1.5);
        zoomIn.play();


        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    updateProgress(i, 55);
                    Thread.sleep(55);
                }
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(event -> {

            Parent loginParent = null;
            try {
                loginParent = FXMLLoader.load(getClass().getResource("/view/serverStartWindowForm.fxml"));

            Scene loginScene = new Scene(loginParent);
            Stage loginStage = new Stage();
            loginStage.setResizable(false);
            Image icon = new Image(getClass().getResourceAsStream("/view/img/logo.png"));
            loginStage.getIcons().add(icon);
            loginStage.setTitle("WhisperWave");
            loginStage.setScene(loginScene);
            loginStage.show();
               new Thread(() -> {
                   playSound("view/meadia/loading.mp3");
               }).start();

                // Close the welcome stage
                ((Stage) progressBar.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        new Thread(task).start();


    }

    private void playSound(String sound) {
        try {
            new Player(new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(sound))).play();
        } catch (JavaLayerException e) {
            new Alert(Alert.AlertType.ERROR, "Audio not available").show();
        }
    }
}
