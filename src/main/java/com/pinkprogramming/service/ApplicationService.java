package com.pinkprogramming.service;

import com.pinkprogramming.dto.AttendeeDto;
import com.pinkprogramming.dto.VolunteerDto;

import java.util.List;

public interface ApplicationService {

    Long createVolunteer(VolunteerDto volunteerDto);

    VolunteerDto getVolunteer(String id);

    List<VolunteerDto> getVolunteers();

    void updateVolunteer(VolunteerDto volunteerDto, String id);

    void deleteVolunteer(String id);

    // Solution create attendee
    /*Long createAttendee(AttendeeDto attendeeDto);*/

    // Solution get attendees
    /*List<AttendeeDto> getAttendees();*/

    // Solution delete attendee
    /*void deleteAttendee(String id);*/
}
