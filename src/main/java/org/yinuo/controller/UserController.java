package org.yinuo.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import org.yinuo.domain.SystemConfig;
import org.yinuo.utils.Configs;
import org.yinuo.utils.Utils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public AnchorPane root;
    public ComboBox<String> userTypes;
    public ComboBox<String> userGroups;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> configValues = Configs.getConfigValues(SystemConfig.KeyEnum.用户类型.name());
        userTypes.setItems(FXCollections.observableArrayList(configValues));

        List<String> configValues1 = Configs.getConfigValues(SystemConfig.KeyEnum.用户分组.name());
        userGroups.setItems(FXCollections.observableArrayList(configValues1));
    }

    public void add(ActionEvent event) {
        Utils.dialog(Utils.getWindows(root),"fxml/user_form.fxml");
    }
}
