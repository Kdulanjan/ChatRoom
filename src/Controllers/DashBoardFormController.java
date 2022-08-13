package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane DashBoardContext;
    public AnchorPane OneContex;

    public void NOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../views/clientOneForm.fxml"));
        DashBoardContext.getChildren().add(parent);
    }

    public void GOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../views/ClientTwoForm.fxml"));
        DashBoardContext.getChildren().add(parent);
    }
    public void OnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../views/ClientTwoForm.fxml"));
        DashBoardContext.getChildren().add(parent);
    }

    public void AOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../views/ClientThreeForm.fxml"));
        DashBoardContext.getChildren().add(parent);
    }

    public void IOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../views/ClientFourForm.fxml"));
        DashBoardContext.getChildren().add(parent);
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)OneContex.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/LoginForm.fxml"))));
        stage.centerOnScreen();
    }
}
