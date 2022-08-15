package Controllers;

import LogIn.LoginFormController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.NavigateForm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class LoginController {
    public TextField textName;
    public Label lblDate;
    public Label lblTime;
    public static String username;
    public AnchorPane LoginContex;


    public void initialize() throws IOException {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        LoadDateAndTime();
    }


    private void LoadDateAndTime() {


        Timeline clock=new Timeline(new KeyFrame(Duration.ZERO , e->{
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond());
        }

        ),new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        if (!textName.getText().isEmpty()){
            username=textName.getText();
            NavigateForm.newUi(LoginContex,"ChatRoom");
        }
    }
}
