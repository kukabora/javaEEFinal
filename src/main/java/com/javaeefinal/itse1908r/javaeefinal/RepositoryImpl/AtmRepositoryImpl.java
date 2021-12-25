package com.javaeefinal.itse1908r.javaeefinal.RepositoryImpl;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Atm;
import com.javaeefinal.itse1908r.javaeefinal.Entities.AtmStatus;
import com.javaeefinal.itse1908r.javaeefinal.EntityManager;
import com.javaeefinal.itse1908r.javaeefinal.Repositories.AtmRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class AtmRepositoryImpl implements AtmRepository {
    @Inject
    EntityManager em;

    @Override
    public List<Atm> findAll() {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Query query = entityManager.createQuery("SELECT s FROM Atm s");
            List<Atm> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Atm findById(Long id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT s FROM Atm s WHERE s.id = %d", id);
            Query query = entityManager.createQuery(sql);
            Atm result = (Atm) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Atm changeServicepointStatusById(int id, int status) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Atm atm = entityManager.find(Atm.class, id);
            AtmStatus atmstatus = entityManager.find(AtmStatus.class, atm.getStatus().getId());
            atm.setStatus(atmstatus);
            entityManager.persist(atm);
            return atm;
        } catch (NoResultException e){
            return null;
        }
    }
}
