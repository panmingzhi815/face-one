package org.yinuo.utils;

import lombok.Getter;
import lombok.Setter;
import org.yinuo.domain.SystemConfig;

import java.util.*;

public class Configs {

    private static Map<String, String> config = new HashMap<>();
    private static Map<String,String> defaultConfig = getDefaultMap();

    public static List<String> getConfigValues(String key){
        String orDefault = config.getOrDefault(key, defaultConfig.get(key));
        return Arrays.asList(orDefault.split("，"));
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
        defaultMap.put(SystemConfig.KeyEnum.用户类型.name(),"学生，老师");
        defaultMap.put(SystemConfig.KeyEnum.用户状态.name(),"正常，停用");
        defaultMap.put(SystemConfig.KeyEnum.用户分组.name(),"初一1班，初一2班");
        defaultMap.put(SystemConfig.KeyEnum.设备状态.name(),"正常，停用");
        defaultMap.put(SystemConfig.KeyEnum.设备分组.name(),"大门，教学楼，办公楼，食堂");
        return defaultMap;
    }

}
