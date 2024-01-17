package com.pinkprogramming.service;

import com.pinkprogramming.dto.VolunteerDto;
import com.pinkprogramming.entity.Volunteer;
import com.pinkprogramming.exception.EntityNotFoundException;
import com.pinkprogramming.mapper.Mapper;
import com.pinkprogramming.repository.VolunteerRepository;
import com.pinkprogramming.response.VolunteerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final String VOLUNTEER_DOES_NOT_EXIST_ERROR_MSG= "A volunteer with the id %s does not exist";

    @Autowired
    private VolunteerRepository volunteerRepository;


    @Override
    public Long createVolunteer(VolunteerDto volunteerDto) {
        Volunteer volunteer = Mapper.mapFromVolunteerDtoToVolunteer(volunteerDto);
        volunteerRepository.save(volunteer);
        return volunteer.getId();
    }

    @Override
    public VolunteerDto getVolunteer(String id) {
        Volunteer volunteer = volunteerRepository.findById(Long.parseLong(id)).orElseThrow(() -> new EntityNotFoundException(String.format(VOLUNTEER_DOES_NOT_EXIST_ERROR_MSG, id)));
        return Mapper.mapFromVolunteerTOVolunteerDto(volunteer);
    }

    @Override
    public List<VolunteerDto> getVolunteers() {
        List<VolunteerDto> volunteerDtoList = new ArrayList<>();
        volunteerRepository.findAll().forEach(volunteer -> volunteerDtoList.add(createVolunteerDto(volunteer)));
        return volunteerDtoList;
    }

    @Override
    public void updateVolunteer(VolunteerDto volunteerDto, String id) {
        Volunteer volunteer = volunteerRepository.findById(Long.parseLong(id)).orElseThrow(() -> new EntityNotFoundException(String.format(VOLUNTEER_DOES_NOT_EXIST_ERROR_MSG, id)));
        volunteer.setName(volunteerDto.getName());
        volunteer.setLastName(volunteerDto.getLastName());
        volunteer.setPosition(volunteerDto.getPosition());
        volunteerRepository.save(volunteer);

    }

    @Override
    public void deleteVolunteer(String id) {
        Volunteer volunteer = volunteerRepository.findById(Long.parseLong(id)).orElseThrow(() -> new EntityNotFoundException(String.format(VOLUNTEER_DOES_NOT_EXIST_ERROR_MSG, id)));
        volunteerRepository.delete(volunteer);
    }


    private VolunteerDto createVolunteerDto (Volunteer volunteer) {
        return Mapper.mapFromVolunteerTOVolunteerDto(volunteer);
    }
}
