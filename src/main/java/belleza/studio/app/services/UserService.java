package belleza.studio.app.services;

import belleza.studio.app.models.entities.UserEntity;
import belleza.studio.app.models.entities.enums.RoleNameEnum;
import belleza.studio.app.models.service.UserRegistrationServiceModel;
import belleza.studio.app.models.service.UserUpdateServiceModel;

import java.util.List;

public interface UserService {

    void seedUsers();

    List<UserEntity> getAllUsers();

    boolean usernameExists(String username);

    void addUser(UserRegistrationServiceModel userRegistrationServiceModel);

    UserEntity getUserById(long id);

    void updateUser(UserUpdateServiceModel userUpdateServiceModel);

    void deleteUser(long id);
}
