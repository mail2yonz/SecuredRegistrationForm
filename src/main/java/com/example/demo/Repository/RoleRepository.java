package com.example.demo.Repository;

import com.example.demo.Entity.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole,Long> {

    UserRole findByRole(String role);
}
