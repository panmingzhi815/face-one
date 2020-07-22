package org.yinuo.service;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import javax.persistence.EntityManager;

@Transactional
public class BaseService {
    @Inject
    protected Provider<EntityManager> provider;

}
