package com.javaeefinal.itse1908r.javaeefinal.RepositoryImpl;

import com.javaeefinal.itse1908r.javaeefinal.Entities.AtmStatus;
import com.javaeefinal.itse1908r.javaeefinal.EntityManager;
import com.javaeefinal.itse1908r.javaeefinal.Repositories.AtmStatusRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class AtmStatusRepositoryImpl implements AtmStatusRepository {
    @Inject
    EntityManager em;

    @Override
    public List<AtmStatus> findAll() {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Query query = entityManager.createQuery("SELECT s FROM AtmStatus s");
            List<AtmStatus> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public AtmStatus findById(Long id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT s FROM AtmStatus s WHERE s.id = %d", id);
            Query query = entityManager.createQuery(sql);
            AtmStatus result = (AtmStatus) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }
}
