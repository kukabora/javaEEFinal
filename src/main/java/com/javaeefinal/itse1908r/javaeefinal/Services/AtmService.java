package com.javaeefinal.itse1908r.javaeefinal.Services;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Atm;
import com.javaeefinal.itse1908r.javaeefinal.Repositories.AtmRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
@RunWith(JUnit4.class)
public class AtmService {
    @Inject
    AtmRepository atmRepository;

    @Test
    public List<Atm> getAllAtms() {return atmRepository.findAll();}

    @Test
    public Atm getAtmById(Long id) {return atmRepository.findById(id);}

    @Test
    public Atm updateStatus(int id, int status) {return atmRepository.changeServicepointStatusById(id, status);}

}
