package org.yinuo.controller;

import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.yinuo.domain.SystemConfig;
import org.yinuo.service.ConfigService;
import org.yinuo.service.LoginService;
import org.yinuo.utils.Configs;
import org.yinuo.utils.Utils;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ConfigController implements Initializable {
    @Inject
    private ConfigService configService;
    public TextField userTypes;
    public TextField userStatus;
    public TextField userGroups;
    public TextField deviceStatus;
    public TextField deviceGroups;
    public AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CompletableFuture.supplyAsync(configService::list).thenAccept(systemConfigs -> {
            Map<String, String> map = systemConfigs.stream().collect(Collectors.toMap(SystemConfig::configKey, SystemConfig::configValue));
            Configs.updateConfig(map);

            Platform.runLater(()->{
                userTypes.setText(Configs.getConfigValue(SystemConfig.KeyEnum.userTypes.name()));
                userStatus.setText(Configs.getConfigValue(SystemConfig.KeyEnum.userStatus.name()));
                userGroups.setText(Configs.getConfigValue(SystemConfig.KeyEnum.userGroups.name()));
                deviceStatus.setText(Configs.getConfigValue(SystemConfig.KeyEnum.deviceStatus.name()));
                deviceGroups.setText(Configs.getConfigValue(SystemConfig.KeyEnum.deviceGroups.name()));
            });
        });
    }

    public void save(ActionEvent event) {
        SystemConfig systemConfig1 = new SystemConfig().configKey(SystemConfig.KeyEnum.userTypes.name()).configValue(userTypes.getText());
        SystemConfig systemConfig2 = new SystemConfig().configKey(SystemConfig.KeyEnum.userStatus.name()).configValue(userStatus.getText());
        SystemConfig systemConfig3 = new SystemConfig().configKey(SystemConfig.KeyEnum.userGroups.name()).configValue(userGroups.getText());
        SystemConfig systemConfig4 = new SystemConfig().configKey(SystemConfig.KeyEnum.deviceStatus.name()).configValue(deviceStatus.getText());
        SystemConfig systemConfig5 = new SystemConfig().configKey(SystemConfig.KeyEnum.deviceGroups.name()).configValue(deviceGroups.getText());

        CompletableFuture.runAsync(()->configService.saveAll(systemConfig1,systemConfig2,systemConfig3,systemConfig4,systemConfig5))
                .whenComplete((aVoid, throwable) -> {
                    if(throwable != null){
                        Utils.showError(Utils.getWindows(root),throwable);
                    }else {
                        Utils.showInfo(Utils.getWindows(root),"操作成功");
                    }
                });
    }

    public void reset(ActionEvent event) {
        userTypes.setText(Configs.getConfigDefaultValue(SystemConfig.KeyEnum.userTypes.name()));
        userStatus.setText(Configs.getConfigDefaultValue(SystemConfig.KeyEnum.userStatus.name()));
        userGroups.setText(Configs.getConfigDefaultValue(SystemConfig.KeyEnum.userGroups.name()));
        deviceStatus.setText(Configs.getConfigDefaultValue(SystemConfig.KeyEnum.deviceStatus.name()));
        deviceGroups.setText(Configs.getConfigDefaultValue(SystemConfig.KeyEnum.deviceGroups.name()));
        save(event);
    }
}
