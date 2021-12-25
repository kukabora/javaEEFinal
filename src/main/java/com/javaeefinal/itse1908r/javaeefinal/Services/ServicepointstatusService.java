package com.javaeefinal.itse1908r.javaeefinal.Services;

import com.javaeefinal.itse1908r.javaeefinal.Entities.AtmStatus;
import com.javaeefinal.itse1908r.javaeefinal.Repositories.AtmStatusRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
@RunWith(JUnit4.class)
public class ServicepointstatusService {
    @Inject
    AtmStatusRepository servicepointstatusRepository;

    @Test
    public List<AtmStatus> getAllServicepointStatuses() {return servicepointstatusRepository.findAll();}

    @Test
    public AtmStatus getServicepointStatsById(Long id) {return servicepointstatusRepository.findById(id);}
}
