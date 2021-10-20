package tuannd.demo.data.service.impl;


import tuannd.demo.data.model.Role;
import tuannd.demo.data.model.User;
import tuannd.demo.data.model.UserRole;
import tuannd.demo.data.repository.RoleRepository;
import tuannd.demo.data.repository.UserRepository;
import tuannd.demo.data.repository.UserRoleRepository;
import tuannd.demo.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userroleRepository;


    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return  userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(long id) {
        return  userRepository.getOne(id);
    }

    @Override
    public long countAll() {
        return userRepository.count();
    }

    @Override
    public void delete(User id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public boolean register(User user) {
        // Check username exists
        if (userRepository.findByUsername(user.getUserName()) != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<Role> role = new HashSet<>();
        role.add(roleRepository.findByName("USER"));
        user.setRoles(role);
        userRepository.save(user);
        return true;
    }

    @Override
    public ArrayList<UserRole> findUserRole() {
        return userroleRepository.findUserRole();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameCheckExist(String username,long id) {
        return userRepository.findByUsernameCheckExist(username,id);
    }

    @Override
    public User findByEmailCheckExist(String email,long id) {
        return userRepository.findByEmailCheckExist(email,id);
    }

    @Override
    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }

    public String findIdByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user.getId()+"";
    }
}
