package Controllers;

import LogIn.LoginFormController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.NavigateForm;

import java.io.IOException;

public class LoginController {
    public TextField textName;
    public Label lblDate;
    public Label lblTime;
    public static String username;
    public AnchorPane LoginContex;

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        if (!textName.getText().isEmpty()){
            username=textName.getText();
            NavigateForm.newUi(LoginContex,"ChatRoom");
        }
    }
}
