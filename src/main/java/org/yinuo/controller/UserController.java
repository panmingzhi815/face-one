package org.yinuo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import org.yinuo.domain.SystemConfig;
import org.yinuo.utils.Configs;
import org.yinuo.utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public AnchorPane root;
    public ComboBox<String> userTypes;
    public ComboBox<String> userGroups;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTypes.setItems(Configs.getConfigValues(SystemConfig.KeyEnum.userTypes.name()));
        userGroups.setItems(Configs.getConfigValues(SystemConfig.KeyEnum.userGroups.name()));
    }

    public void add(ActionEvent event) {
        Utils.dialog(Utils.getWindows(root),"fxml/user_form.fxml");
    }
}
