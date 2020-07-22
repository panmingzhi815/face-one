package org.yinuo;

import cn.hutool.core.thread.ThreadUtil;
import com.google.inject.*;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.yinuo.domain.SystemConfig;
import org.yinuo.service.ConfigService;
import org.yinuo.utils.Configs;
import org.yinuo.utils.FXMLLoaders;

import javax.inject.Provider;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JavaFxApplication extends Application {

    @Inject
    private ConfigService configService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        log.info("启动人脸识别管理平台");
        primaryStage.setTitle("人脸识别管理平台");
        primaryStage.setScene(new Scene(new StartPane()));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getIcons().add(new Image("img/face icon.png"));
        primaryStage.setOnCloseRequest((e)->System.exit(1));

        primaryStage.show();
        Platform.setImplicitExit(true);
        ThreadUtil.execAsync(()->{
            try {
                log.info("加载数据服务。。。");
                Injector injector = Guice.createInjector(new JpaPersistModule("jpa-unit"));
                injector.getInstance(PersistService.class).start();
                injector.injectMembers(this);
                FXMLLoaders.setInjector(injector);

                log.info("初始化系统配置");
                Map<String, String> config = configService.list().stream().collect(Collectors.toMap(SystemConfig::configKey, SystemConfig::configValue));
                Configs.updateConfig(config);

                log.info("所有准备己完成，准备登录");
                Platform.runLater(()-> primaryStage.setScene(new Scene(FXMLLoaders.load("fxml/login.fxml"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
