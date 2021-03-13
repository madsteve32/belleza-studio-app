package belleza.studio.app.services.impl;

import belleza.studio.app.models.entities.UserEntity;
import belleza.studio.app.models.entities.UserRoleEntity;
import belleza.studio.app.models.entities.enums.UserRole;
import belleza.studio.app.repositories.UserRepository;
import belleza.studio.app.repositories.UserRoleRepository;
import belleza.studio.app.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRole.ADMIN);

            userRoleRepository.save(adminRole);

            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName("Steven");
            userEntity.setLastName("Lyutov");
            userEntity.setEmail("steve@gmail.com");
            userEntity.setUsername("admin");
            userEntity.setPassword(passwordEncoder.encode("admin"));
            userEntity.setRoles(List.of(adminRole));

            userRepository.save(userEntity);
        }
    }
}
