package com.pinkprogramming.controller;

import com.pinkprogramming.dto.VolunteerDto;
import com.pinkprogramming.mapper.Mapper;
import com.pinkprogramming.request.VolunteerRequest;
import com.pinkprogramming.response.CreateVolunteerResponse;
import com.pinkprogramming.response.VolunteerResponse;
import com.pinkprogramming.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)

public class Controller {

    @Autowired
    private ApplicationService applicationService;


    @Operation(summary = "Creates a new volunteer", security = @SecurityRequirement(name = "basicAuth"))
    @PostMapping("/volunteers")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CreateVolunteerResponse> createVolunteer(@Valid @RequestBody VolunteerRequest volunteerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new CreateVolunteerResponse(applicationService.createVolunteer(Mapper.mapFromVolunteerRequestToVolunteerDto(volunteerRequest))));
    }

    @Operation(summary = "Gets a specific volunteer", security = @SecurityRequirement(name = "basicAuth"))
    @GetMapping("/volunteers/{id}")
    @Secured("ROLE_USER")
    public ResponseEntity<VolunteerResponse> getVolunteer(@Parameter(description = "Volunterr's id") @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).
                body(Mapper.mapFromVolunteerDtoToVolunteerResponse(applicationService.getVolunteer(id)));
    }

    @Operation(summary = "Gets a list of volunteers", security = @SecurityRequirement(name = "basicAuth"))
    @GetMapping("/volunteers")
    @Secured("ROLE_USER")
    public ResponseEntity<List<VolunteerResponse>> getVolunteers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(applicationService.getVolunteers().stream().map(this::createVolunteerResponse).collect(Collectors.toList()));
    }

    @Operation(summary = "Updates an existing volunteer", security = @SecurityRequirement(name = "basicAuth"))
    @PutMapping("/volunteers/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> updateVolunteer(@Valid @RequestBody VolunteerRequest volunteerRequest,
                                                                   @Parameter(description = "Volunterr's id") @PathVariable String id) {
        applicationService.updateVolunteer(Mapper.mapFromVolunteerRequestToVolunteerDto(volunteerRequest), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Deletes an existing volunteer", security = @SecurityRequirement(name = "basicAuth"))
    @DeleteMapping("/volunteers/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteVolunteer(@Parameter(description = "Volunterr's id") @PathVariable String id) {
        applicationService.deleteVolunteer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Says hi")
    @GetMapping("/sayHi")
    public String sayHi() {
        return "Hi";
    }

    private VolunteerResponse createVolunteerResponse (VolunteerDto volunteerDto) {
       return Mapper.mapFromVolunteerDtoToVolunteerResponse(volunteerDto);
    }
}
