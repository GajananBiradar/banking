package com.houseclay.zebra.service.impl;

import com.houseclay.zebra.dto.EditUserDTO;
import com.houseclay.zebra.dto.UserDTO;
import com.houseclay.zebra.dto.UserListDTO;
import com.houseclay.zebra.model.Role;
import com.houseclay.zebra.model.User;
import com.houseclay.zebra.model.common.BaseTimeStamp;
import com.houseclay.zebra.repository.RoleRepository;
import com.houseclay.zebra.repository.UserRepository;
import com.houseclay.zebra.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null) {
            log.error("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }else {
            log.info("User Found in the database: {}",username);

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public String loginUser(String username, String password) {
        return null;
    }

    @Override
    public User registerUser(User user, String loggedInUser) {
        log.info("Saving new user {} to the database",user);
        user.setBaseTimeStamp(BaseTimeStamp.builder().created_by(loggedInUser).created_on(new Date()).build());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        Optional<User> user= Optional.ofNullable(userRepository.findByUsername(userName));
        return user;
    }

    @Override
    public UserDTO findById(UUID id) {
        return userRepository.findById(id).isPresent()? this.maptoUserDTO(userRepository.findById(id).get()) : null;
    }

    @Override
    public String updateUser(UUID id, EditUserDTO editUserDTO, String username) {

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){

            BaseTimeStamp baseTimeStamp = BaseTimeStamp.builder().
                    created_on(editUserDTO.getBaseTimeStamp().getCreated_on())
                    .created_by(editUserDTO.getBaseTimeStamp().getCreated_by())
                    .changed_by(username).changed_on(new Date()).build();
            User updatedUser = User.builder().id(editUserDTO.getId()).firstName(editUserDTO.getFirstName())
                            .lastName(editUserDTO.getLastName()).username(editUserDTO.getUsername()).password(user.get().getPassword())
                            .contactNumber(editUserDTO.getContactNumber()).notes(editUserDTO.getNotes())
                             .roles(new ArrayList<>())
                            .active(editUserDTO.isActive()).isEmailVerified(editUserDTO.isEmailVerified())
                            .isPhoneVerified(editUserDTO.isPhoneVerified()).baseTimeStamp(baseTimeStamp).build();

            userRepository.save(updatedUser);

            //add roles to the user
            if(editUserDTO.getRoles().size() !=0) {
                for(String role: editUserDTO.getRoles())
                {this.addRoleToUser(editUserDTO.getUsername(), role);}
            }
            else {
                this.addRoleToUser(updatedUser.getUsername(), "");
            }
            return "User updated successfully";
        }

        return "User not found";
    }

    @Override
    public String deleteUserById(UUID id) {
        //load the user, check if the role of the user is a primary , then don't delete it.
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            log.info("User to be deleted found in database");
            boolean hasPrimaryRole = user.get().getRoles().stream()
                    .anyMatch(role ->
                        role.getType().equals("Primary")
                    );
            if(!hasPrimaryRole) {
                userRepository.deleteById(id);
                return "User "+user.get().getFirstName()+" "+user.get().getLastName()+" deleted successfully";
            }
            return "User "+user.get().getFirstName()+" "+user.get().getLastName()+ " cannot be deleted. User has primary roles";
        }
        return "User Not Found";
    }
    private UserDTO maptoUserDTO(User user) {

        return UserDTO.builder().id(user.getId()).roles(new ArrayList<>(user.getRoles())).
                username(user.getUsername()).firstName(user.getFirstName()).lastName(user.getLastName())
                        .contactNumber(user.getContactNumber()).active(user.isActive())
                        .isEmailVerified(user.isEmailVerified()).isPhoneVerified(user.isPhoneVerified())
                        .notes(user.getNotes()).baseTimeStamp(user.getBaseTimeStamp())
                .build();
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        System.out.println(user);
        System.out.println(role);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public String findNameByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user!=null){
            return user.getFirstName() + " " + user.getLastName();
        }

        return "User Not Found";
    }

    @Override
    public ArrayList<UserListDTO> fetchAllUsersList() {
        return (ArrayList<UserListDTO>) userRepository.findAll().parallelStream().map(this::sanitizeToList).collect(Collectors.toList());
    }

    private UserListDTO sanitizeToList(User user) {
        return UserListDTO.builder().user_id(String.valueOf(user.getId()))
                .username(user.getUsername()).name(user.getFirstName()+ " "+ user.getLastName())
                .build();
    }


}
