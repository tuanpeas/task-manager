package tuannd.demo.data.repository;

import tuannd.demo.data.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role findByName(String name);

    @Query(value = "select * from role  where id = :id", nativeQuery = true)
    Role findRoleById(@Param("id") int id);

    @Query(value = "SELECT * FROM  role WHERE  id NOT IN (SELECT role_id FROM user_role  WHERE user_id =:userId)",nativeQuery = true)
    List<Role> findRoleNotExistOfUser(@Param("userId") int userId);

    @Query(value = "SELECT * FROM  role WHERE  id  IN (SELECT role_id FROM user_role  WHERE user_id =:userId)",nativeQuery = true)
    List<Role> findRoleExistOfUser(@Param("userId") int userId);

}
