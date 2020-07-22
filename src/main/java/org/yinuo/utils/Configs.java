package org.yinuo.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.yinuo.domain.SystemConfig;

import java.util.HashMap;
import java.util.Map;

public class Configs {

    private static Map<String, String> config = new HashMap<>();
    private static Map<String,String> defaultConfig = getDefaultMap();

    public static ObservableList<String> getConfigValues(String key){
        String orDefault = config.getOrDefault(key, defaultConfig.get(key));
        return FXCollections.observableArrayList(orDefault.split("，"));
    }

    public static String getConfigValue(String key){
        return config.getOrDefault(key, defaultConfig.get(key));
    }

    public static String getConfigDefaultValue(String key){
        return defaultConfig.getOrDefault(key,"");
    }

    public static void updateConfig(Map<String,String> map){
        config.putAll(map);
    }

    private static Map<String,String> getDefaultMap(){
        HashMap<String, String> defaultMap = new HashMap<>();
        defaultMap.put(SystemConfig.KeyEnum.userTypes.name(),"学生，老师");
        defaultMap.put(SystemConfig.KeyEnum.userStatus.name(),"正常，停用");
        defaultMap.put(SystemConfig.KeyEnum.userGroups.name(),"初一1班，初一2班");
        defaultMap.put(SystemConfig.KeyEnum.deviceStatus.name(),"正常，停用");
        defaultMap.put(SystemConfig.KeyEnum.deviceGroups.name(),"大门，教学楼，办公楼，食堂");
        return defaultMap;
    }

}
