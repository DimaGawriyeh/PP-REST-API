package service;

import com.pinkprogramming.dto.VolunteerDto;
import com.pinkprogramming.entity.Volunteer;
import com.pinkprogramming.exception.EntityNotFoundException;
import com.pinkprogramming.repository.VolunteerRepository;
import com.pinkprogramming.service.ApplicationServiceImpl;
import configuration.TestApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {TestApplicationConfiguration.class})
public class ApplicationServiceTest {

    private final String VOLUNTEER_NAME = "pink";
    private final String VOLUNTEER_LAST_NAME = "programming";
    private final String VOLUNTEER_POSITION = "pink volunteer";
    private final String VOLUNTEER_ID= "1";


    @Mock
    private VolunteerRepository volunteerRepository;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Test
    public void createVolunteerTest() {
        when(volunteerRepository.save(any(Volunteer.class))).thenReturn(any(Volunteer.class));
        applicationService.createVolunteer(createVolunteerDto());
        verify(volunteerRepository, times(1)).save(any(Volunteer.class));
    }

    @Test
    public void getVolunteerTest() {
        Volunteer volunteer = createVolunteer();
        when(volunteerRepository.findById(any(Long.class))).thenReturn(Optional.of(volunteer));
        VolunteerDto result = applicationService.getVolunteer(volunteer.getId().toString());
        verify(volunteerRepository, times(1)).findById(any(Long.class));
        assertEquals(VOLUNTEER_NAME, result.getName());
        assertEquals(VOLUNTEER_LAST_NAME, result.getLastName());
        assertEquals(VOLUNTEER_POSITION, result.getPosition());
    }

    @Test
    public void getVolunteerTest_throwsEntityNotFoundException() {
        when(volunteerRepository.findById(any(Long.class))).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> volunteerRepository.findById(any(Long.class)));
    }

    @Test
    public void getVolunteersTest() {
        Volunteer volunteer = createVolunteer();
        when(volunteerRepository.findAll()).thenReturn(List.of(volunteer));
        List<VolunteerDto> result = applicationService.getVolunteers();
        verify(volunteerRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(VOLUNTEER_NAME, result.get(0).getName());
        assertEquals(VOLUNTEER_LAST_NAME, result.get(0).getLastName());
        assertEquals(VOLUNTEER_POSITION, result.get(0).getPosition());
    }

    @Test
    public void updateVolunteerTest() {
        when(volunteerRepository.findById(any(Long.class))).thenReturn(Optional.of(createVolunteer()));
        when(volunteerRepository.save(any(Volunteer.class))).thenReturn(any(Volunteer.class));
        applicationService.updateVolunteer(createVolunteerDto(), VOLUNTEER_ID);
        verify(volunteerRepository, times(1)).save(any(Volunteer.class));
    }

    @Test
    public void deleteVolunteerTest() {
        Volunteer volunteer = createVolunteer();
        when(volunteerRepository.findById(any(Long.class))).thenReturn(Optional.of(volunteer));
        applicationService.deleteVolunteer(volunteer.getId().toString());
        verify(volunteerRepository, times(1)).delete(any(Volunteer.class));
    }


    private VolunteerDto createVolunteerDto(){
        return VolunteerDto.builder()
                .name(VOLUNTEER_NAME)
                .lastName(VOLUNTEER_LAST_NAME)
                .position(VOLUNTEER_POSITION)
                .build();
    }

    private Volunteer createVolunteer(){
        return Volunteer.builder()
                .id(Long.parseLong(VOLUNTEER_ID))
                .name(VOLUNTEER_NAME)
                .lastName(VOLUNTEER_LAST_NAME)
                .position(VOLUNTEER_POSITION)
                .build();
    }
}
