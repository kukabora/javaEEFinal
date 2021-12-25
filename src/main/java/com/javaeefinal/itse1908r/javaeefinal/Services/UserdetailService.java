package com.javaeefinal.itse1908r.javaeefinal.Services;

import com.javaeefinal.itse1908r.javaeefinal.Entities.User;
import com.javaeefinal.itse1908r.javaeefinal.Entities.Userdetail;
import com.javaeefinal.itse1908r.javaeefinal.Repositories.UserdetailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
@RunWith(JUnit4.class)
public class UserdetailService {
    @Inject
    UserdetailRepository userdetailRepository;

    @Test
    public List<Userdetail> getAllUserdetails() {return userdetailRepository.findAll();}

    @Test
    public Userdetail getUserdetailById(Long id) {return userdetailRepository.findById(id);}
}
