package org.yinuo.controller;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.yinuo.domain.SystemConfig;
import org.yinuo.utils.Configs;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrivilegeController implements Initializable {
    public ComboBox<String> userTypes;
    public ComboBox<String> userGroups;
    public ComboBox<String> deviceStatus;
    public ComboBox<String> deviceGroups;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> configValues = Configs.getConfigValues(SystemConfig.KeyEnum.deviceStatus.name());
        deviceStatus.setItems(FXCollections.observableArrayList(configValues));

        List<String> configValues1 = Configs.getConfigValues(SystemConfig.KeyEnum.deviceGroups.name());
        deviceGroups.setItems(FXCollections.observableArrayList(configValues1));

        List<String> configValues2 = Configs.getConfigValues(SystemConfig.KeyEnum.userTypes.name());
        userTypes.setItems(FXCollections.observableArrayList(configValues2));

        List<String> configValues3 = Configs.getConfigValues(SystemConfig.KeyEnum.userGroups.name());
        userGroups.setItems(FXCollections.observableArrayList(configValues3));
    }
}
