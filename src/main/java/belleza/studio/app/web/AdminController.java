package belleza.studio.app.web;

import belleza.studio.app.models.binding.UserRegistrationBindingModel;
import belleza.studio.app.models.entities.UserEntity;
import belleza.studio.app.models.entities.enums.RoleNameEnum;
import belleza.studio.app.models.service.UserRegistrationServiceModel;
import belleza.studio.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    // Display list of users
    @GetMapping("/admin-panel")
    public String adminPanel(Model model) {
        List<UserEntity> listUsers = userService.getAllUsers();

        model.addAttribute("listUsers", listUsers);

        return "admin-panel";
    }

    @ModelAttribute("registrationBindingModel")
    public UserRegistrationBindingModel createBindingModel() {
        return new UserRegistrationBindingModel();
    }

    @GetMapping("/add-user")
    public String add(Model model) {

        model.addAttribute("roles", RoleNameEnum.values());

        return "add-user";
    }

    @PostMapping("/add-user")
    public String addConfirm(@Valid UserRegistrationBindingModel userRegistrationBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);

            return "redirect:/add-user";
        }

        if (userService.usernameExists(userRegistrationBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("userExistsError", true);

            return "redirect:/add-user";
        }

        UserRegistrationServiceModel userRegistrationServiceModel = modelMapper
                .map(userRegistrationBindingModel, UserRegistrationServiceModel.class);

        userService.addUser(userRegistrationServiceModel);

        return "redirect:/admin-panel";
    }
}
