package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinkprogramming.dto.VolunteerDto;
import com.pinkprogramming.request.VolunteerRequest;
import com.pinkprogramming.service.ApplicationService;
import configuration.TestApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestApplicationConfiguration.class})
public class ControllerTest {

    private final String VOLUNTEER_NAME = "pink";
    private final String VOLUNTEER_LAST_NAME = "programming";
    private final String VOLUNTEER_POSITION = "pink volunteer";

    @MockBean
    ApplicationService applicationService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "Admin", roles = {"ADMIN", "USER"})
    public void createVolunteersTest () throws Exception {
        mockMvc.perform(post("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest())))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.volunteerId").exists());
    }

    @Test
    @WithMockUser(username = "User")
    public void createVolunteersWithUnauthorizedUserTest () throws Exception {
        mockMvc.perform(post("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest())))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    public void createVolunteersWihNoAuthenticationCredentialsTest () throws Exception {
        mockMvc.perform(post("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest())))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ADMIN", "USER"})
    public void createVolunteersWithInvalidVolunteerRequestsTest () throws Exception {
        mockMvc.perform(post("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest(null, "last name", "position"))))
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(post("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest("name", null, "position"))))
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(post("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest("name", "last name", null))))
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(post("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest("*".repeat(21), "last name", "position"))))
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(post("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest("name", "*".repeat(21), "position"))))
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(post("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest("name", "last name", "*".repeat(21)))))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "User")
    public void getVolunteerTest () throws Exception {
        when(applicationService.getVolunteer(any(String.class))).thenReturn(createVolunteerDto());
        mockMvc.perform(get("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
        jsonPath("$.name").value(VOLUNTEER_NAME),
        jsonPath("$.lastName").value(VOLUNTEER_LAST_NAME),
        jsonPath("$.position").value(VOLUNTEER_POSITION));
    }

    @Test
    public void getVolunteerWihNoAuthenticationCredentialsTest () throws Exception {
        when(applicationService.getVolunteer(any(String.class))).thenReturn(createVolunteerDto());
        mockMvc.perform(get("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "User")
    public void getVolunteersTest () throws Exception {
        when(applicationService.getVolunteers()).thenReturn(List.of(createVolunteerDto()));
        mockMvc.perform(get("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$[0].name").value(VOLUNTEER_NAME),
                        jsonPath("$[0].lastName").value(VOLUNTEER_LAST_NAME),
                        jsonPath("$[0].position").value(VOLUNTEER_POSITION));
    }

    @Test
    public void getVolunteersWithNoAuthenticationCredentialsTest () throws Exception {
        when(applicationService.getVolunteers()).thenReturn(List.of(createVolunteerDto()));
        mockMvc.perform(get("/volunteers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ADMIN", "USER"})
    public void updateVolunteerTest () throws Exception {
        mockMvc.perform(put("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest())))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "User")
    public void updateVolunteerWithUnauthorizedUserTest () throws Exception {
        mockMvc.perform(put("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest())))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    public void updateVolunteerWithNoAuthenticationCredentialsTest () throws Exception {
        mockMvc.perform(put("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest())))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ADMIN", "USER"})
    public void updateVolunteersWithInvalidVolunteerRequestsTest () throws Exception {
        mockMvc.perform(put("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest(null, "last name", "position"))))
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(put("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest("name", null, "position"))))
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(put("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest("name", "last name", null))))
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(put("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest("*".repeat(21), "last name", "position"))))
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(put("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest("name", "*".repeat(21), "position"))))
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(put("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVolunteerRequest("name", "last name", "*".repeat(21)))))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ADMIN", "USER"})
    public void deleteVolunteerTest () throws Exception {
        mockMvc.perform(delete("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "User")
    public void deleteVolunteerWithUnauthorizedUserTest () throws Exception {
        mockMvc.perform(delete("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    public void deleteVolunteerWithNoAuthenticationCredentialsTest () throws Exception {
        mockMvc.perform(delete("/volunteers/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void sayHiTest () throws Exception {
        mockMvc.perform(get("/sayHi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private VolunteerRequest createVolunteerRequest(){
        return VolunteerRequest.builder()
                .name(VOLUNTEER_NAME)
                .lastName(VOLUNTEER_LAST_NAME)
                .position(VOLUNTEER_POSITION)
                .build();
    }

    private VolunteerDto createVolunteerDto(){
        return VolunteerDto.builder()
                .name(VOLUNTEER_NAME)
                .lastName(VOLUNTEER_LAST_NAME)
                .position(VOLUNTEER_POSITION)
                .build();
    }

    private VolunteerRequest createVolunteerRequest(String name, String lasName, String position){
        return VolunteerRequest.builder()
                .name(name)
                .lastName(lasName)
                .position(position)
                .build();
    }
}
