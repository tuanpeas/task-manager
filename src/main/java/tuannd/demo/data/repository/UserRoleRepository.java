package tuannd.demo.data.repository;

import tuannd.demo.data.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserRoleRepository extends CrudRepository<UserRole,Integer> {
    @Query(value = "select * from user_role", nativeQuery = true)
    ArrayList<UserRole> findUserRole();
    @Query(value = "select * from user_role  where user_id=:userId", nativeQuery = true)
    ArrayList<UserRole> findRoleIdByUserId(@Param("userId") long userId);

}
