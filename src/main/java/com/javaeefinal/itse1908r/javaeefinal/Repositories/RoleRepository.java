package com.javaeefinal.itse1908r.javaeefinal.Repositories;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> findAll();
    Role findByName(String name);
}
