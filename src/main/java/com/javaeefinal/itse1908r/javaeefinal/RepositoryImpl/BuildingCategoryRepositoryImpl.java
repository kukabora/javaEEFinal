package com.javaeefinal.itse1908r.javaeefinal.RepositoryImpl;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Building;
import com.javaeefinal.itse1908r.javaeefinal.Entities.BuildingsCategory;
import com.javaeefinal.itse1908r.javaeefinal.EntityManager;
import com.javaeefinal.itse1908r.javaeefinal.Repositories.BuildingsCategoryRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class BuildingCategoryRepositoryImpl implements BuildingsCategoryRepository {
    @Inject
    EntityManager em;


    @Override
    public List<BuildingsCategory> findAll() {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Query query = entityManager.createQuery("SELECT b FROM BuildingsCategory b");
            List<BuildingsCategory> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public BuildingsCategory findById(Long id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT b FROM BuildingsCategory b WHERE b.id = %d", id);
            Query query = entityManager.createQuery(sql);
            BuildingsCategory result = (BuildingsCategory) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public BuildingsCategory deleteById(int id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            BuildingsCategory buildingsCategory = entityManager.find(BuildingsCategory.class, id);
            entityManager.remove(buildingsCategory);
            return buildingsCategory;
        } catch (NoResultException e){
            return null;
        }
    }
}
