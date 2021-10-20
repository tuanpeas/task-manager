package tuannd.demo.data.service;


import tuannd.demo.data.model.User;
import tuannd.demo.data.model.UserRole;

import java.util.ArrayList;
import java.util.List;


public interface UserService {


    List<User> findAll();

    User findOne(long id);

    long countAll();

    void delete(User id);

    boolean register(User user);

    ArrayList<UserRole> findUserRole();

    User findByUsername(String username);
    User findByUsernameCheckExist(String username, long id);
    User findByEmailCheckExist(String email, long id);
    User update(User user);



}
