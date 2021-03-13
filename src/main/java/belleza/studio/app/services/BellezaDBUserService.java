package belleza.studio.app.services;

import belleza.studio.app.models.entities.UserEntity;
import belleza.studio.app.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class BellezaDBUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public BellezaDBUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username" + username + "was not found"));

        return mapToUserDetails(userEntity);
    }

    private UserDetails mapToUserDetails(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity
                .getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                .collect(Collectors.toList());

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
