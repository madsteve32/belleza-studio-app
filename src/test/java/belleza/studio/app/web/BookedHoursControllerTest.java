package belleza.studio.app.web;

import belleza.studio.app.repositories.BookedHoursRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class BookedHoursControllerTest {

    private static final String BOOKED_HOURS_CONTROLLER_PREFIX = "/booking";

    @Autowired
    private BookedHoursRepository bookedHoursRepository;

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

    @Test
    void bookAnHour() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(BOOKED_HOURS_CONTROLLER_PREFIX + "/hour")
        .param("firstName", "Test")
        .param("lastName", "Testerov")
        .param("bookedDateAndTime", "2021-04-27T12:00")
        .param("number", "0898786534")
        .param("serviceType", "Haircut")
        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, bookedHoursRepository.count());
    }
}
