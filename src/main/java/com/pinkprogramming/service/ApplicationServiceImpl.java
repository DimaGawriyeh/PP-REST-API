package com.pinkprogramming.service;

import com.pinkprogramming.dto.VolunteerDto;
import com.pinkprogramming.mapper.Mapper;
import com.pinkprogramming.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private VolunteerRepository volunteerRepository;


    @Override
    public void createVolunteer(VolunteerDto volunteerDto) {
        volunteerRepository.save(Mapper.mapFromVolunteerDto(volunteerDto));
    }
}
