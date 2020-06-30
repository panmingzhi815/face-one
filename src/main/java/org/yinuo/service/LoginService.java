package org.yinuo.service;

import org.yinuo.domain.LoginUser;
import org.yinuo.utils.EntityManagers;

import javax.persistence.TypedQuery;

public class LoginService {

    String default_username = "admin";
    String default_password = "123456";

    public void checkAdmin() {
        EntityManagers.execute(entityManager -> {
            TypedQuery<LoginUser> query = entityManager.createQuery("from LoginUser where username = :username", LoginUser.class);
            query.setParameter("username", default_username);
            int maxResults = query.getResultList().size();
            if(maxResults == 0){
                LoginUser loginUser = new LoginUser().username(default_username).password(default_password);
                entityManager.persist(loginUser);
            }
        });
    }

    public LoginUser checkLoginUser(String username, String password) {
        return EntityManagers.query(entityManager -> {
            TypedQuery<LoginUser> query = entityManager.createQuery("from LoginUser where username = :username and password = :password", LoginUser.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        });
    }
}
