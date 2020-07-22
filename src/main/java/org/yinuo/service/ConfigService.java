package org.yinuo.service;

import org.yinuo.domain.SystemConfig;

import javax.inject.Singleton;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ConfigService extends BaseService {

    public List<SystemConfig> list(){
        TypedQuery<SystemConfig> managerQuery =  provider.get().createQuery("from SystemConfig ", SystemConfig.class);
        return managerQuery.getResultList();
    }

    public void saveAll(SystemConfig... systemConfigs) {
        Query query =  provider.get().createQuery("delete from SystemConfig where configKey in (:configKey)");
        query.setParameter("configKey", Arrays.stream(systemConfigs).map(SystemConfig::configKey).collect(Collectors.toList()));
        query.executeUpdate();

        for (SystemConfig systemConfig : systemConfigs) {
            provider.get().persist(systemConfig);
        }
    }
}
