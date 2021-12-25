package com.javaeefinal.itse1908r.javaeefinal.Repositories;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Atm;
import com.javaeefinal.itse1908r.javaeefinal.Entities.AtmStatus;

import java.util.List;

public interface AtmStatusRepository {
    List<AtmStatus> findAll();
    AtmStatus findById(Long id);
}
