package org.yinuo.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;
import org.yinuo.service.LoginService;
import org.yinuo.utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class LoginController implements Initializable {
    public HBox headerBox;
    public TextField username;
    public PasswordField password;
    @Inject
    private LoginService loginService;

    public void initialize(URL location, ResourceBundle resources) {
        Utils.Drag(headerBox);
        ThreadUtil.execAsync(loginService::checkAdmin);
    }

    public void login(ActionEvent actionEvent) {
        if(StrUtil.isEmpty(username.getText()) || StrUtil.isEmpty(password.getText())){
            return;
        }

        Utils.disableEventTarget(actionEvent,true);
        CompletableFuture.supplyAsync(() -> loginService.checkLoginUser(username.getText(), password.getText()))
                .whenComplete((loginUser, throwable) -> Utils.disableEventTarget(actionEvent,false))
                .thenAccept(loginUser -> Utils.forward(Utils.getWindows(headerBox), "fxml/faceOne.fxml", 1024, 768))
                .exceptionally(throwable -> {
                    log.error("登录失败",throwable);
                    password.getStyleClass().add("text-danger");
                    username.getStyleClass().add("text-danger");
                    return null;
                });
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(1);
    }
}
