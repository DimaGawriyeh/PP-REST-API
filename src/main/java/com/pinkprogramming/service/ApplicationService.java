package com.pinkprogramming.service;

import com.pinkprogramming.dto.VolunteerDto;

import java.util.List;

public interface ApplicationService {

    Long createVolunteer(VolunteerDto volunteerDto);

    VolunteerDto getVolunteer(String id);

    List<VolunteerDto> getVolunteers();

    void updateVolunteer(VolunteerDto volunteerDto, String id);

    void deleteVolunteer(String id);
}
