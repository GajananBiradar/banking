package com.houseclay.zebra.service;

import com.houseclay.zebra.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {


    String registerRole(Role role, String loggedInUser);

    public List<Role> findAll();
}
