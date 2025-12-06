package paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Paint extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Paint Application");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/paint/view/start.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}
