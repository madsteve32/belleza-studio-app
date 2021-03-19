package belleza.studio.app.repositories;

import belleza.studio.app.models.entities.UserRoleEntity;
import belleza.studio.app.models.entities.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByRole(RoleNameEnum roleName);
}
