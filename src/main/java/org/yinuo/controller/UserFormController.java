package org.yinuo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.yinuo.utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {
    public AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void no(ActionEvent event) {
        Utils.getWindows(root).close();
    }

    public void yes(ActionEvent event) {
        Utils.getWindows(root).close();
    }
}
