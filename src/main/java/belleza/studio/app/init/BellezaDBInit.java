package belleza.studio.app.init;

import belleza.studio.app.services.ServiceEntityService;
import belleza.studio.app.services.UserRoleService;
import belleza.studio.app.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BellezaDBInit implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final ServiceEntityService serviceEntityService;

    public BellezaDBInit(UserService userService, UserRoleService userRoleService, ServiceEntityService serviceEntityService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.serviceEntityService = serviceEntityService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        serviceEntityService.initServices();
    }
}
