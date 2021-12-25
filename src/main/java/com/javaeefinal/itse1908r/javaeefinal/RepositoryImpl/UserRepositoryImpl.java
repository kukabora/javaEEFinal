package com.javaeefinal.itse1908r.javaeefinal.RepositoryImpl;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Building;
import com.javaeefinal.itse1908r.javaeefinal.Entities.Role;
import com.javaeefinal.itse1908r.javaeefinal.Entities.User;
import com.javaeefinal.itse1908r.javaeefinal.EntityManager;
import com.javaeefinal.itse1908r.javaeefinal.Repositories.UserRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Inject
    EntityManager em;

    @Override
    public List<User> findAll() {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u");
            List<User> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public User findById(Long id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT u FROM User u WHERE u.id = %d", id);
            Query query = entityManager.createQuery(sql);
            User result = (User) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public User authenticate(String login, String password) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT u FROM User u WHERE u.login = '%s'  AND u.password = '%s'", login, password);
            Query query = entityManager.createQuery(sql);
            User result = (User) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public User deleteById(int id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
            return user;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public User updatePasswordById(int id, String password) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            User user = entityManager.find(User.class, id);
            user.setPassword(password);
            entityManager.persist(user);
            return user;
        } catch (NoResultException e){
            return null;
        }
    }


    @Override
    public User updatePasswordByLogin(String login, String password) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {

            User userFind = findByLogin(login);
            User user = entityManager.find(User.class, userFind.getId());
            user.setPassword(password);
            entityManager.persist(user);
            return user;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public User createNewUser(String login, String password) {
        javax.persistence.EntityManager entityManager = em.manager();
        User user = findByLogin(login);
        if (user == null){
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            Role role = entityManager.find(Role.class, 1);
            user.setRole(role);
            entityManager.persist(user);
            return user;
        }
        user = new User();
        return user;
    }

    @Override
    public User findByLogin(String login) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT u FROM User u WHERE u.login = '%s'", login);
            Query query = entityManager.createQuery(sql);
            User result = (User) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }
}
