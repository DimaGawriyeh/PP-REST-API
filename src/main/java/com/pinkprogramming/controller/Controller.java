package com.pinkprogramming.controller;

import com.pinkprogramming.mapper.Mapper;
import com.pinkprogramming.request.VolunteerRequest;
import com.pinkprogramming.service.ApplicationService;
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


    @PostMapping
    public ResponseEntity<Void> createVolunteer(@Valid @RequestBody VolunteerRequest volunteerRequest) {
        applicationService.createVolunteer(Mapper.mapFromVolunteerRequest(volunteerRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public String sayHi() {
        return "Hi";
    }
}
