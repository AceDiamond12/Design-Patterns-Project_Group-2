package paint.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartController {
    @FXML
    private void goToMain(ActionEvent e) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
            Scene main = new Scene(FXMLLoader.load(getClass().getResource("/paint/view/main.fxml")));
            stage.setScene(main);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
