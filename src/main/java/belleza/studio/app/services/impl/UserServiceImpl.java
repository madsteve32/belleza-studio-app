package belleza.studio.app.services.impl;

import belleza.studio.app.models.entities.UserEntity;
import belleza.studio.app.models.entities.UserRoleEntity;
import belleza.studio.app.models.entities.enums.RoleNameEnum;
import belleza.studio.app.models.service.UserRegistrationServiceModel;
import belleza.studio.app.repositories.UserRepository;
import belleza.studio.app.repositories.UserRoleRepository;
import belleza.studio.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final BellezaDBUserService bellezaDBUserService;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(BellezaDBUserService bellezaDBUserService, UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.bellezaDBUserService = bellezaDBUserService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(RoleNameEnum.ADMIN);

            UserRoleEntity makeupArtist = new UserRoleEntity();
            makeupArtist.setRole(RoleNameEnum.MAKEUP_ARTIST);

            UserRoleEntity manicurist = new UserRoleEntity();
            manicurist.setRole(RoleNameEnum.MANICURIST);

            UserRoleEntity hairdresser = new UserRoleEntity();
            hairdresser.setRole(RoleNameEnum.HAIRDRESSER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(makeupArtist);
            userRoleRepository.save(manicurist);
            userRoleRepository.save(hairdresser);

            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName("Steven");
            userEntity.setLastName("Lyutov");
            userEntity.setEmail("steve@gmail.com");
            userEntity.setUsername("admin");
            userEntity.setPassword(passwordEncoder.encode("admin"));
            userEntity.setRole(adminRole);

            userRepository.save(userEntity);
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserEntity newUser = modelMapper.map(userRegistrationServiceModel, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));



        userRepository.save(newUser);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
