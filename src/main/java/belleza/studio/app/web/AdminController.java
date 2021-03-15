package belleza.studio.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin-panel")
    public String adminPanel() {
        return "admin-panel";
    }
}
