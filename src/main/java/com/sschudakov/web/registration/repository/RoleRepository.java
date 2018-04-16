package com.sschudakov.web.registration.repository;

import com.sschudakov.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String roleName);
}



