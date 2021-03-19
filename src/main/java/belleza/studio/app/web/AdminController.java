package belleza.studio.app.web;

import belleza.studio.app.models.binding.UserRegistrationBindingModel;
import belleza.studio.app.models.entities.UserEntity;
import belleza.studio.app.models.entities.enums.RoleNameEnum;
import belleza.studio.app.models.service.UserRegistrationServiceModel;
import belleza.studio.app.models.service.UserUpdateServiceModel;
import belleza.studio.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

        return "register";
    }

    @PostMapping("/add-user")
    public String addConfirm(@Valid UserRegistrationBindingModel userRegistrationBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);

            return "redirect:/register";
        }

        if (userService.usernameExists(userRegistrationBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("userExistsError", true);

            return "redirect:/register";
        }

        UserRegistrationServiceModel userRegistrationServiceModel = modelMapper
                .map(userRegistrationBindingModel, UserRegistrationServiceModel.class);

        userService.addUser(userRegistrationServiceModel);

        return "redirect:/admin-panel";
    }

    @GetMapping("/update-user/{id}")
    public String update(@PathVariable("id") long id, Model model) {
        // Get user from DB
        UserEntity userEntity = userService.getUserById(id);
        UserUpdateServiceModel userUpdateServiceModel = modelMapper.map(userEntity, UserUpdateServiceModel.class);

        // Set user as a model attribute to pre-populate the form
        model.addAttribute("user", userUpdateServiceModel);
        model.addAttribute("roles", RoleNameEnum.values());

        return "update";
    }

    @PostMapping("/save-user")
    public String updateConfirm(@ModelAttribute("user") UserUpdateServiceModel userUpdateServiceModel) {

        System.out.println(userUpdateServiceModel.toString());

        // save user in DB after updated information
        userService.updateUser(userUpdateServiceModel);

        return "redirect:/admin-panel";
    }
}
