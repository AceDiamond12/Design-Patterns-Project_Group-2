package paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Paint extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Set the title of the window
        // تعيين عنوان النافذة
        stage.setTitle("Paint Application");

        // Load the Start Screen (start.fxml)
        // تحميل شاشة البداية من ملف FXML
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/paint/view/start.fxml")));
        
        // Set the scene and show the window
        // عرض المشهد وإظهار النافذة
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { 
        // Launch the application
        // تشغيل التطبيق
        launch(args); 
    }
}