package com.pinkprogramming.controller;

import com.pinkprogramming.mapper.Mapper;
import com.pinkprogramming.request.VolunteerRequest;
import com.pinkprogramming.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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


    @Operation(summary = "Creates a new voluneer", security = @SecurityRequirement(name = "basicAuth"))
    @PostMapping("/volunteers")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> createVolunteer(@Valid @RequestBody VolunteerRequest volunteerRequest) {
        applicationService.createVolunteer(Mapper.mapFromVolunteerRequest(volunteerRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Says hi")
    @GetMapping
    public String sayHi() {
        return "Hi";
    }
}
