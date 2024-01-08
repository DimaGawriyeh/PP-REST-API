package com.pinkprogramming.controller;

import com.pinkprogramming.mapper.Mapper;
import com.pinkprogramming.request.VolunteerRequest;
import com.pinkprogramming.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    private ApplicationService applicationService;


    @Operation(summary = "Creates a new voluneer")
    @PostMapping("/volunteers")
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
