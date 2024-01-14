package com.pinkprogramming.mapper;

import com.pinkprogramming.dto.VolunteerDto;
import com.pinkprogramming.entity.Volunteer;
import com.pinkprogramming.request.VolunteerRequest;


public class Mapper {

    public static Volunteer mapFromVolunteerDto(VolunteerDto volunteerDto) {

        return Volunteer.builder()
                .name(volunteerDto.getName())
                .lastName(volunteerDto.getLastName())
                .position(volunteerDto.getPosition())
                .build();
    }

    public static VolunteerDto mapFromVolunteer(Volunteer volunteer) {

        return VolunteerDto.builder()
                .name(volunteer.getName())
                .lastName(volunteer.getLastName())
                .position(volunteer.getPosition())
                .build();
    }

    public static VolunteerDto mapFromVolunteerRequest(VolunteerRequest volunteerRequest) {

        return VolunteerDto.builder()
                .name(volunteerRequest.getName())
                .lastName(volunteerRequest.getLastName())
                .position(volunteerRequest.getPosition())
                .build();
    }
}
