package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientThreeFormController {
    public AnchorPane ClientThreeContex;

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)ClientThreeContex.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/LoginForm.fxml"))));
        stage.centerOnScreen();
    }
}
