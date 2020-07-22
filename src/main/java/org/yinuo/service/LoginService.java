package org.yinuo.service;

import org.yinuo.domain.LoginUser;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;

@Singleton
public class LoginService extends BaseService {

    String default_username = "admin";
    String default_password = "123456";

    public void checkAdmin() {
        TypedQuery<LoginUser> query = provider.get().createQuery("from LoginUser where username = :username", LoginUser.class);
        query.setParameter("username", default_username);
        int maxResults = query.getResultList().size();
        if(maxResults == 0){
            LoginUser loginUser = new LoginUser().username(default_username).password(default_password);
            provider.get().persist(loginUser);
        }
    }

    public LoginUser checkLoginUser(String username, String password) {
        TypedQuery<LoginUser> query =  provider.get().createQuery("from LoginUser where username = :username and password = :password", LoginUser.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
}
