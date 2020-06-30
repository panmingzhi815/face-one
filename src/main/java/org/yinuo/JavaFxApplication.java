package org.yinuo;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import cn.hutool.core.thread.ThreadUtil;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.yinuo.domain.SystemConfig;
import org.yinuo.service.ConfigService;
import org.yinuo.utils.Configs;
import org.yinuo.utils.EntityManagers;
import org.yinuo.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JavaFxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent load = Utils.loadFxml("fxml/login.fxml");
        Scene scene = new Scene(load);
        primaryStage.setTitle("人脸识别管理平台");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getIcons().add(new Image("img/face icon.png"));
        primaryStage.show();

        ThreadUtil.execAsync(()->{
            try {
                EntityManagers.entityManagerFactory();

                Map<String, String> config = new ConfigService().list().stream().collect(Collectors.toMap(SystemConfig::configKey, SystemConfig::configValue));
                Configs.updateConfig(config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
