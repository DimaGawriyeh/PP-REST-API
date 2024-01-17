package com.pinkprogramming.mapper;

import com.pinkprogramming.dto.VolunteerDto;
import com.pinkprogramming.entity.Volunteer;
import com.pinkprogramming.request.VolunteerRequest;
import com.pinkprogramming.response.VolunteerResponse;


public class Mapper {

    public static Volunteer mapFromVolunteerDtoToVolunteer(VolunteerDto volunteerDto) {

        return Volunteer.builder()
                .name(volunteerDto.getName())
                .lastName(volunteerDto.getLastName())
                .position(volunteerDto.getPosition())
                .build();
    }

    public static VolunteerDto mapFromVolunteerTOVolunteerDto(Volunteer volunteer) {

        return VolunteerDto.builder()
                .name(volunteer.getName())
                .lastName(volunteer.getLastName())
                .position(volunteer.getPosition())
                .build();
    }

    public static VolunteerDto mapFromVolunteerRequestToVolunteerDto(VolunteerRequest volunteerRequest) {

        return VolunteerDto.builder()
                .name(volunteerRequest.getName())
                .lastName(volunteerRequest.getLastName())
                .position(volunteerRequest.getPosition())
                .build();
    }

    public static VolunteerResponse mapFromVolunteerDtoToVolunteerResponse(VolunteerDto volunteerDto) {

        return VolunteerResponse.builder()
                .name(volunteerDto.getName())
                .lastName(volunteerDto.getLastName())
                .position(volunteerDto.getPosition())
                .build();
    }


}
