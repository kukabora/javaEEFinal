package com.javaeefinal.itse1908r.javaeefinal.RepositoryImpl;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Role;
import com.javaeefinal.itse1908r.javaeefinal.EntityManager;
import com.javaeefinal.itse1908r.javaeefinal.Repositories.RoleRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {
    @Inject
    EntityManager em;

    @Override
    public List<Role> findAll() {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Query query = entityManager.createQuery("SELECT r FROM Role r");
            List<Role> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Role findByName(String name) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT r FROM Role r WHERE r.name = ", name);
            Query query = entityManager.createQuery(sql);
            Role result = (Role) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }
}
