package com.houseclay.zebra.service;

import com.houseclay.zebra.dto.EditUserDTO;
import com.houseclay.zebra.dto.UserDTO;
import com.houseclay.zebra.dto.UserListDTO;
import com.houseclay.zebra.model.Role;
import com.houseclay.zebra.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    public String loginUser(String username,String password);
    public User registerUser(User user,String loggedInUser); // this is to save user
    public List<User> findAll(); //make is pageable - 10 users at once
    public Optional<User> findByUserName(String userName);
    public UserDTO findById(UUID id);

    public String updateUser(UUID id, EditUserDTO editUserDTO, String username);
    public String deleteUserById(UUID id);
    public void addRoleToUser(String username, String roleName);
    public Role saveRole(Role role);

    public String findNameByUsername(String username);

    public ArrayList<UserListDTO> fetchAllUsersList();
}
;