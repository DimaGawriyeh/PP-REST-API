package com.pinkprogramming.mapper;

import com.pinkprogramming.dto.AttendeeDto;
import com.pinkprogramming.dto.VolunteerDto;
import com.pinkprogramming.entity.Attendee;
import com.pinkprogramming.entity.Volunteer;
import com.pinkprogramming.request.AttendeeRequest;
import com.pinkprogramming.request.VolunteerRequest;
import com.pinkprogramming.response.AttendeeResponse;
import com.pinkprogramming.response.VolunteerResponse;


public class Mapper {

    // Volunteer mappers

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

    // Attendee mappers

    public static Attendee mapFromAttendeeDtoToAttendee(AttendeeDto attendeeDto) {

        return Attendee.builder()
                .name(attendeeDto.getName())
                .lastName(attendeeDto.getLastName())
                .build();
    }

    public static AttendeeDto mapFromAttendeeToAttendeeDto(Attendee attendee) {

        return AttendeeDto.builder()
                .name(attendee.getName())
                .lastName(attendee.getLastName())
                .build();
    }

    public static AttendeeDto mapFromAttendeeRequestToAttendeeDto(AttendeeRequest attendeeRequest) {

        return AttendeeDto.builder()
                .name(attendeeRequest.getName())
                .lastName(attendeeRequest.getLastName())
                .build();
    }

    public static AttendeeResponse mapFromAttendeeDtoToAttendeeResponse(AttendeeDto attendeeDto) {

        return AttendeeResponse.builder()
                .name(attendeeDto.getName())
                .lastName(attendeeDto.getLastName())
                .build();
    }


}
