package com.pinkprogramming.controller;

import com.pinkprogramming.dto.AttendeeDto;
import com.pinkprogramming.dto.VolunteerDto;
import com.pinkprogramming.mapper.Mapper;
import com.pinkprogramming.request.AttendeeRequest;
import com.pinkprogramming.request.VolunteerRequest;
import com.pinkprogramming.response.AttendeeResponse;
import com.pinkprogramming.response.CreateAttendeeResponse;
import com.pinkprogramming.response.CreateVolunteerResponse;
import com.pinkprogramming.response.VolunteerResponse;
import com.pinkprogramming.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private ApplicationService applicationService;


    @Operation(summary = "Creates a new volunteer", security = @SecurityRequirement(name = "basicAuth"))
    @PostMapping("/volunteers")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CreateVolunteerResponse> createVolunteer(@Valid @RequestBody VolunteerRequest volunteerRequest) {
        log.info("Received create volunteer request for {}", volunteerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new CreateVolunteerResponse(applicationService.createVolunteer(Mapper.mapFromVolunteerRequestToVolunteerDto(volunteerRequest))));
    }

    @Operation(summary = "Gets a specific volunteer", security = @SecurityRequirement(name = "basicAuth"))
    @GetMapping("/volunteers/{id}")
    @Secured("ROLE_USER")
    public ResponseEntity<VolunteerResponse> getVolunteer(@Parameter(description = "Volunterr's id") @PathVariable String id) {
        log.info("Received get volunteer request for volunteer id {}", id);
        return ResponseEntity.status(HttpStatus.OK).
                body(Mapper.mapFromVolunteerDtoToVolunteerResponse(applicationService.getVolunteer(id)));
    }

    @Operation(summary = "Gets a list of volunteers", security = @SecurityRequirement(name = "basicAuth"))
    @GetMapping("/volunteers")
    @Secured("ROLE_USER")
    public ResponseEntity<List<VolunteerResponse>> getVolunteers() {
        log.info("Received get volunteers request");
        return ResponseEntity.status(HttpStatus.OK)
                .body(applicationService.getVolunteers().stream().map(this::createVolunteerResponse).collect(Collectors.toList()));
    }

    @Operation(summary = "Updates an existing volunteer", security = @SecurityRequirement(name = "basicAuth"))
    @PutMapping("/volunteers/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> updateVolunteer(@Valid @RequestBody VolunteerRequest volunteerRequest,
                                                                   @Parameter(description = "Volunterr's id") @PathVariable String id) {
        log.info("Received update volunteer request for volunteer id {} with request body {}", id, volunteerRequest);
        applicationService.updateVolunteer(Mapper.mapFromVolunteerRequestToVolunteerDto(volunteerRequest), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Deletes an existing volunteer", security = @SecurityRequirement(name = "basicAuth"))
    @DeleteMapping("/volunteers/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteVolunteer(@Parameter(description = "Volunterr's id") @PathVariable String id) {
        log.info("Received get volunteer request for volunteer id {}", id);
        applicationService.deleteVolunteer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Says hi")
    @GetMapping("/sayHi")
    public String sayHi() {
        log.info("Received say hi request");
        return "Hi";
    }

    private VolunteerResponse createVolunteerResponse (VolunteerDto volunteerDto) {
       return Mapper.mapFromVolunteerDtoToVolunteerResponse(volunteerDto);
    }

    // Solution create attendee
    /*@Operation(summary = "Creates a new attendee", security = @SecurityRequirement(name = "basicAuth"))
    @PostMapping("/attendees")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<CreateAttendeeResponse> createAttendee(@Valid @RequestBody AttendeeRequest attendeeRequest) {
        //log.info("Received create attendee request for {}", attendeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new CreateAttendeeResponse(applicationService.createAttendee(Mapper.mapFromAttendeeRequestToAttendeeDto(attendeeRequest))));
    }*/

    // Solution get attendees
   /* @Operation(summary = "Gets a list of attendees", security = @SecurityRequirement(name = "basicAuth"))
    @GetMapping("/attendees")
    //@Secured("ROLE_USER")
    public ResponseEntity<List<AttendeeResponse>> getAttendees() {
        //log.info("Received get attendees request");
        return ResponseEntity.status(HttpStatus.OK)
                .body(applicationService.getAttendees().stream().map(this::createAttendeeResponse).collect(Collectors.toList()));
    }

    private AttendeeResponse createAttendeeResponse (AttendeeDto attendeeDto) {
        return Mapper.mapFromAttendeeDtoToAttendeeResponse(attendeeDto);
    }*/

    // Solution delete attendee
    /*@Operation(summary = "Deletes an existing attendee", security = @SecurityRequirement(name = "basicAuth"))
    @DeleteMapping("/attendees/{id}")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteAttendee(@Parameter(description = "Attendee's id") @PathVariable String id) {
        //log.info("Received delete attendee request for attendee id {}", id);
        applicationService.deleteAttendee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }*/

}
