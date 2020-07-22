package org.yinuo.utils;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class FXMLLoaders {

    private static Injector injector;

    public static void setInjector(Injector injector) {
        Preconditions.checkArgument(injector != null,"己经注入过injector");
        FXMLLoaders.injector = injector;
    }

    public static Parent load(String fxml){
        try {
            return new FXMLLoader(ClassLoader.getSystemResource(fxml),null,null,param -> injector.getInstance(param), Charsets.UTF_8).load();
        } catch (IOException e) {
            throw new RuntimeException("加载fxml出错",e);
        }
    }
}
