package belleza.studio.app.models.binding;

import belleza.studio.app.models.entities.enums.RoleNameEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationBindingModel {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private RoleNameEnum role;

    public UserRegistrationBindingModel() {
    }

    @NotEmpty
    @Size(min = 3, message = "First name must be at least 3 characters.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty
    @Size(min = 3, message = "Last name must be at least 3 characters.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty
    @Size(min = 3, message = "Username must be at least 3 characters.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
    @Email(message = "Email must be valid.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleNameEnum getRole() {
        return role;
    }

    public void setRole(RoleNameEnum role) {
        this.role = role;
    }
}
