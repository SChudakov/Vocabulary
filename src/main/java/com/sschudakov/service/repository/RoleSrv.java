package com.sschudakov.service.repository;

import com.sschudakov.dao.springdata.RoleRepository;
import com.sschudakov.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleSrv {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleSrv(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return this.roleRepository.getByName(name);
    }
}
