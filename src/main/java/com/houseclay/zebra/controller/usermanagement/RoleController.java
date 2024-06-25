package com.houseclay.zebra.controller.usermanagement;

import com.houseclay.zebra.model.Role;
import com.houseclay.zebra.service.RoleService;
import com.houseclay.zebra.utils.ApplicationConfig;
import com.houseclay.zebra.utils.JwtTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "User Management")
@RequestMapping(value = "/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final RoleService roleService;

    private JwtTokenUtils jwtTokenUtils = new JwtTokenUtils(new ApplicationConfig());


    @ApiOperation(value = "Add a new role",response = String.class)
    @PostMapping(value = "/v1/register-role")
    public ResponseEntity<String> createNewRole(@RequestBody Role role, @RequestHeader("Authorization") String token) {
        String username = jwtTokenUtils.extractUsernamefromToken(token);
        return ResponseEntity.ok().body(roleService.registerRole(role,username));

    }

    @ApiOperation(value = "Get all roles", response = Role.class)
    @GetMapping("/v1/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok().body(roleService.findAll());
    }
}
