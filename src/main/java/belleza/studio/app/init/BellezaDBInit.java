package belleza.studio.app.init;

import belleza.studio.app.services.StudioFeatureService;
import belleza.studio.app.services.UserRoleService;
import belleza.studio.app.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BellezaDBInit implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final StudioFeatureService studioFeatureService;

    public BellezaDBInit(UserService userService, UserRoleService userRoleService, StudioFeatureService studioFeatureService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.studioFeatureService = studioFeatureService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        studioFeatureService.initServices();
    }
}
