package belleza.studio.app.service.impl;

import belleza.studio.app.models.entities.UserEntity;
import belleza.studio.app.models.entities.UserRoleEntity;
import belleza.studio.app.models.entities.enums.RoleNameEnum;
import belleza.studio.app.repositories.UserRepository;
import belleza.studio.app.services.impl.BellezaDBUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class BellezaDBUserServiceTest {

    private BellezaDBUserService serviceToTest;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void setUp() {
        serviceToTest = new BellezaDBUserService(mockUserRepository);
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class, () -> {
                    serviceToTest.loadUserByUsername("user_does_not_exist");
                }
        );
    }

    @Test
    void testExistingUser() {
        // prepare data
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("admin");

        UserRoleEntity roleAdmin = new UserRoleEntity();
        roleAdmin.setRole(RoleNameEnum.ADMIN);

        userEntity.setRole(roleAdmin);

        // configure mocks
        Mockito.when(mockUserRepository.findByUsername("admin"))
                .thenReturn(Optional.of(userEntity));

        // test
        UserDetails userDetails = serviceToTest.loadUserByUsername("admin");

        Assertions.assertEquals(userEntity.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(1, userDetails.getAuthorities().size());

        List<String> authorities = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
    }
}
