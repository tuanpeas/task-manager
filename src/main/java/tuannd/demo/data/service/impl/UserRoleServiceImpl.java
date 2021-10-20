package tuannd.demo.data.service.impl;


import tuannd.demo.data.repository.RoleRepository;
import tuannd.demo.data.service.RoleService;
import tuannd.demo.data.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EntityManager entityManager;


    @Transactional
    @Override
    public boolean insertUserRoleWithRoleName(String name, int userId) {
        try {
            String sql= "INSERT INTO user_role (user_id, role_id ) VALUES (:userId,(SELECT id  FROM role WHERE NAME =:name))";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("userId",userId);
            query.setParameter("name",name);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("insertUserRoleWithRoleName | ex: "+ e.getMessage());
            return false;
        }
    }
}
