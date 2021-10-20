package tuannd.demo.data.service.impl;

import tuannd.demo.data.model.Role;
import tuannd.demo.data.repository.RoleRepository;
import tuannd.demo.data.service.RoleService;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Role> getRoleNameExistOfUser(List<String> roleList, int userId) {
        try {
            List<Role> roleExist = roleRepository.findRoleExistOfUser(userId);
            return roleExist;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getRoleNameNotExistOfUser | ex : " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Transactional
    @Override
    public boolean deleteRoleExist(List<String> roleList, int userId) {
        try {
            String sql ="delete ur from user_role   ur left join role  r on ur.role_id = r.id where ur.user_id = :userId and r.name  not in (:roleList)";
            Query query = (Query) entityManager.createNativeQuery(sql);
            query.setParameterList("roleList",roleList);
            query.setParameter("userId",userId);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteRoleExist | ex : " + e.getMessage());
            return  false;
        }

    }
}
