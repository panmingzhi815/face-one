package org.yinuo.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.yinuo.domain.SystemConfig;
import org.yinuo.service.ConfigService;
import org.yinuo.utils.Configs;
import org.yinuo.utils.Utils;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ConfigController implements Initializable {

    private final ConfigService configService = new ConfigService();
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
                userTypes.setText(Configs.getConfigValue(SystemConfig.KeyEnum.用户类型.name()));
                userStatus.setText(Configs.getConfigValue(SystemConfig.KeyEnum.用户类型.name()));
                userGroups.setText(Configs.getConfigValue(SystemConfig.KeyEnum.用户分组.name()));
                deviceStatus.setText(Configs.getConfigValue(SystemConfig.KeyEnum.设备状态.name()));
                deviceGroups.setText(Configs.getConfigValue(SystemConfig.KeyEnum.设备分组.name()));
            });
        });
    }

    public void save(ActionEvent event) {
        SystemConfig systemConfig1 = new SystemConfig().configKey(SystemConfig.KeyEnum.用户类型.name()).configValue(userTypes.getText());
        SystemConfig systemConfig2 = new SystemConfig().configKey(SystemConfig.KeyEnum.用户状态.name()).configValue(userStatus.getText());
        SystemConfig systemConfig3 = new SystemConfig().configKey(SystemConfig.KeyEnum.用户分组.name()).configValue(userGroups.getText());
        SystemConfig systemConfig4 = new SystemConfig().configKey(SystemConfig.KeyEnum.设备状态.name()).configValue(deviceStatus.getText());
        SystemConfig systemConfig5 = new SystemConfig().configKey(SystemConfig.KeyEnum.设备分组.name()).configValue(deviceGroups.getText());

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
        userTypes.setText(Configs.getConfigDefaultValue(SystemConfig.KeyEnum.用户类型.name()));
        userStatus.setText(Configs.getConfigDefaultValue(SystemConfig.KeyEnum.用户状态.name()));
        userGroups.setText(Configs.getConfigDefaultValue(SystemConfig.KeyEnum.用户分组.name()));
        deviceStatus.setText(Configs.getConfigDefaultValue(SystemConfig.KeyEnum.设备状态.name()));
        deviceGroups.setText(Configs.getConfigDefaultValue(SystemConfig.KeyEnum.设备分组.name()));
        save(event);
    }
}
