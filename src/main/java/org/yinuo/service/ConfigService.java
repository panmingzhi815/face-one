package org.yinuo.service;

import org.yinuo.domain.SystemConfig;
import org.yinuo.utils.EntityManagers;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigService {

    public List<SystemConfig> list(){
        return EntityManagers.query(entityManager -> {
            TypedQuery<SystemConfig> managerQuery = entityManager.createQuery("from SystemConfig ", SystemConfig.class);
            return managerQuery.getResultList();
        });
    }

    public void saveAll(SystemConfig... systemConfigs) {
        EntityManagers.execute(entityManager -> {
            Query query = entityManager.createQuery("delete from SystemConfig where configKey in (:configKey)");
            query.setParameter("configKey", Arrays.stream(systemConfigs).map(SystemConfig::configKey).collect(Collectors.toList()));
            query.executeUpdate();

            for (SystemConfig systemConfig : systemConfigs) {
                entityManager.persist(systemConfig);
            }
        });
    }
}
