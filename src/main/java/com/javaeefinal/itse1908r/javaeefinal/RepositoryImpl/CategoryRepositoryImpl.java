package com.javaeefinal.itse1908r.javaeefinal.RepositoryImpl;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Category;
import com.javaeefinal.itse1908r.javaeefinal.EntityManager;
import com.javaeefinal.itse1908r.javaeefinal.Repositories.CategoryRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Inject
    EntityManager em;

    @Override
    public List<Category> findAll() {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Query query = entityManager.createQuery("SELECT c FROM Category c");
            List<Category> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Category findById(int id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT c FROM Category c WHERE c.id = %d", id);
            Query query = entityManager.createQuery(sql);
            Category result = (Category) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Category insertCategory(int id, String name, String description) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Category category = new Category(id, name, description);
            entityManager.persist(category);
            return category;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Category deleteById(int id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Category category = entityManager.find(Category.class, id);
            entityManager.remove(category);
            return category;
        } catch (NoResultException e){
            return null;
        }
    }


    @Override
    public Category findByName(String name) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT c FROM Category c WHERE c.name = '%s'", name);
            Query query = entityManager.createQuery(sql);
            Category result = (Category) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }
}
