package org.yinuo.controller;

import javafx.application.Platform;
import javafx.event.EventTarget;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.yinuo.utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FaceOneController implements Initializable {
    public VBox left_menu;
    public ImageView expand_icon;
    public BorderPane borderPane;
    public Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        left_menu.getChildren().forEach(Utils::Drag);
        Utils.Drag(left_menu);
        Utils.Drag(expand_icon.getParent());
    }

    public void expand(MouseEvent mouseEvent) {
        if(left_menu.getPrefWidth() != 80.0D){
            left_menu.setPrefWidth(80.0D);
            expand_icon.setImage(new Image("img/master ctrl.png"));
        }else {
            left_menu.setPrefWidth(200.0D);
            expand_icon.setImage(new Image("img/mirror light ctrl.png"));
        }
    }

    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void user(MouseEvent mouseEvent) {
        borderPane.setCenter(Utils.loadFxml("fxml/user.fxml"));
        title.setText("人脸用户");
    }

    public void device(MouseEvent mouseEvent) {
        borderPane.setCenter(Utils.loadFxml("fxml/device.fxml"));
        title.setText("人脸设备");
    }

    public void privilege(MouseEvent mouseEvent) {
        borderPane.setCenter(Utils.loadFxml("fxml/privilege.fxml"));
        title.setText("人脸授权");
    }

    public void record(MouseEvent mouseEvent) {
        borderPane.setCenter(Utils.loadFxml("fxml/record.fxml"));
        title.setText("通行记录");
    }

    public void lock(MouseEvent mouseEvent) {

    }

    public void config(MouseEvent mouseEvent) {
        borderPane.setCenter(Utils.loadFxml("fxml/config.fxml"));
        title.setText("系统设置");
    }
}
