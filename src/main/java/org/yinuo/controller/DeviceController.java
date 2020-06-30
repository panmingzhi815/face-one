package org.yinuo.controller;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.yinuo.domain.SystemConfig;
import org.yinuo.utils.Configs;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DeviceController implements Initializable {
    public ComboBox<String> deviceStatus;
    public ComboBox<String> deviceGroups;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> configValues = Configs.getConfigValues(SystemConfig.KeyEnum.设备状态.name());
        deviceStatus.setItems(FXCollections.observableArrayList(configValues));

        List<String> configValues1 = Configs.getConfigValues(SystemConfig.KeyEnum.设备分组.name());
        deviceGroups.setItems(FXCollections.observableArrayList(configValues1));
    }
}
