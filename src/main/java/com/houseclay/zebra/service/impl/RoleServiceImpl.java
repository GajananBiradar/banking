package com.houseclay.zebra.service.impl;

import com.houseclay.zebra.model.Role;
import com.houseclay.zebra.model.common.BaseTimeStamp;
import com.houseclay.zebra.repository.RoleRepository;
import com.houseclay.zebra.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired private final RoleRepository roleRepository;

    @Override
    public String registerRole(Role role, String loggedInUser) {
        try{
            role.setBaseTimeStamp(BaseTimeStamp.builder().created_by(loggedInUser).created_on(new Date()).build());
            roleRepository.save(role);
            return "Role saved successfully";
        }catch(Exception e) {
            System.out.println(e);
            return "Role not Saved!";
        }

    }

    @Override
    public List<Role> findAll() {
        System.out.println("Hello");
        return roleRepository.findAll();

    }
}
