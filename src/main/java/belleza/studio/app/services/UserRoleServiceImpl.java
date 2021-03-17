package belleza.studio.app.services;

import belleza.studio.app.models.entities.UserRoleEntity;
import belleza.studio.app.models.entities.enums.RoleNameEnum;
import belleza.studio.app.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

}
