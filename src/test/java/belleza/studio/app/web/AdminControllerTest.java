package belleza.studio.app.web;

import belleza.studio.app.models.entities.enums.RoleNameEnum;
import belleza.studio.app.repositories.UserRepository;
import belleza.studio.app.repositories.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin-panel"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-panel"))
                .andExpect(model().attributeExists("listUsers"));
    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN"})
    void addUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/add-user")
        .param("firstName", "Test1")
        .param("lastName", "Testerov1")
        .param("username", "tester123")
        .param("password", "testerche1")
        .param("email", "test@test.com")
        .param("role", RoleNameEnum.MAKEUP_ARTIST.name())
        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, userRepository.count());
    }
}
