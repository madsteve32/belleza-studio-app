package belleza.studio.app.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class BookedHoursControllerTest {

    private static final String BOOKED_HOURS_CONTROLLER_PREFIX = "/booking";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "kosi", roles = "MAKEUP_ARTIST")
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BOOKED_HOURS_CONTROLLER_PREFIX + "/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("booked-hours-list"))
                .andExpect(model().attributeExists("bookedHours"));
    }
}
