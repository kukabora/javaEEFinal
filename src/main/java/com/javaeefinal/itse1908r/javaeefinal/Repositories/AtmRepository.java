package com.javaeefinal.itse1908r.javaeefinal.Repositories;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Atm;

import java.util.List;

public interface AtmRepository {
    List<Atm> findAll();
    Atm findById(Long id);
    Atm changeServicepointStatusById(int id, int status);
}
