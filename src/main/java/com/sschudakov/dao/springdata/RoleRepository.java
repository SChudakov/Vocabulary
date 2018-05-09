package com.sschudakov.dao.springdata;

import com.sschudakov.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getByName(String name);
}
