package org.acme.resteasy.db;

import org.acme.resteasy.model.User;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserEjb {
    @Inject EntityManager em;

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<User> getUsers(){
        return em.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Transactional
    public void save(User user){
        em.persist(user);
    }

    @Transactional
    public User edit(User user){
        return em.merge(user);
    }

    @Transactional
    public void remove(User user){
        em.remove(user);
    }

    public User find(Long id){
        return em.find(User.class,id);
    }
}
