package org.yinuo.controller;

import cn.hutool.core.util.StrUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.yinuo.domain.SystemConfig;
import org.yinuo.utils.Configs;
import org.yinuo.utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {
    public AnchorPane root;
    public ComboBox<String> userStatus;
    public ComboBox<String> userGroups;
    public ComboBox<String> userTypes;
    public TextField userCode;
    public TextField userName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userStatus.setItems(Configs.getConfigValues(SystemConfig.KeyEnum.userStatus.name()));
        userGroups.setItems(Configs.getConfigValues(SystemConfig.KeyEnum.userGroups.name()));
        userTypes.setItems(Configs.getConfigValues(SystemConfig.KeyEnum.userTypes.name()));
    }

    public void no(ActionEvent event) {
        Utils.getWindows(root).close();
    }

    public void yes(ActionEvent event) {
        if(StrUtil.isEmpty(userCode.getText())){
            userCode.getStyleClass().addAll("alert-danger");
            return;
        }
        Utils.getWindows(root).close();
    }
}
