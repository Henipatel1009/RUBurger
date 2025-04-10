import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for RU Burger
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application
     * @param primaryStage the primary stage
     * @throws Exception if an error occurs during loading
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/MainView.fxml"));
        primaryStage.setTitle("RU Burger");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}