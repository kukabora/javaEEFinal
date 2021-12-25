package com.javaeefinal.itse1908r.javaeefinal.Repositories;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    Category findById(int id);
    Category insertCategory(int id, String name, String description);
    Category deleteById(int id);
    Category findByName(String name);
}
