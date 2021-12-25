package com.javaeefinal.itse1908r.javaeefinal.Repositories;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Building;
import com.javaeefinal.itse1908r.javaeefinal.Entities.BuildingsCategory;

import java.util.List;

public interface BuildingsCategoryRepository {
    List<BuildingsCategory> findAll();
    BuildingsCategory findById(Long id);
    BuildingsCategory deleteById(int id);
}
