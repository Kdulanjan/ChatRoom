package Controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardFormController {
    public AnchorPane DashBoardContext;
    public AnchorPane OneContex;
    public javafx.scene.control.TextArea TextArea;
    public TextField TextFeild;
    public VBox vbox;
    public Label lblTime;
    public Label lblDate;


    private FileChooser fileChooser;
    private File filePath;
    Socket socket;
    String userName;
    BufferedReader reader;
    PrintWriter writer;

    public void initialize() throws IOException {
        LoadDateAndTime();


        userName = LoginController.username;

        try {
            socket = new Socket("localhost", 5000);
            System.out.println("connected to the server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String msg = reader.readLine();
                        int start = msg.indexOf("<");
                        int end = msg.indexOf(">");
                        String message = msg.substring(start + 1, end);
                        String imagePath = msg.substring(end + 1);


                        VBox vBox = new VBox(10);
                        vBox.setAlignment(Pos.BOTTOM_RIGHT);
                        vbox.setAlignment(Pos.TOP_LEFT);
                        vBox.setAlignment(Pos.CENTER_LEFT);

                        Text text = new Text(message);
                        vBox.getChildren().add(text);

                        if (!imagePath.isEmpty()) {

                            File file = new File(imagePath);
                            Image image = new Image(file.toURI().toString());

                            ImageView imageView = new ImageView(image);

                            imageView.setFitHeight(80);
                            imageView.setFitWidth(80);
                            vBox.getChildren().add(imageView);
                        }


                        Platform.runLater(() -> vbox.getChildren().addAll(vBox));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }


    private void LoadDateAndTime() {
        //time date
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock=new Timeline(new KeyFrame(Duration.ZERO , e->{
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond());
        }

        ),new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)OneContex.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/LoginForm.fxml"))));
        stage.centerOnScreen();
    }

   

    public void ImojiOnAction(ActionEvent actionEvent) {
    }

    public void SendOnAction(ActionEvent actionEvent) {
        String msg = TextFeild.getText();
        if (!msg.isEmpty() && filePath != null) {
            writer.println("<" + userName + ": " + msg + ">" + filePath.getPath());
        } else if (filePath != null && msg.isEmpty()) {
            writer.println("<" + userName + ":   >" + filePath.getPath());
        } else if (!msg.isEmpty() && filePath == null) {
            writer.println("<" + userName + ": " + msg + ">" + "");
        }

        TextFeild.clear();

        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);

        }



    }

    public void CamaraOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
    }
}
