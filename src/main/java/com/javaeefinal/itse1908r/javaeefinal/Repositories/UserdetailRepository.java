package com.javaeefinal.itse1908r.javaeefinal.Repositories;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Userdetail;

import java.util.List;

public interface UserdetailRepository {
    List<Userdetail> findAll();
    Userdetail findById(Long id);
    Userdetail deleteById(int id);
}
