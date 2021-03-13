package belleza.studio.app.init;

import belleza.studio.app.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BellezaDBInit implements CommandLineRunner {

    private final UserService userService;

    public BellezaDBInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
    }
}
