package com.pinkprogramming.repository;

import com.pinkprogramming.entity.Volunteer;
import org.springframework.data.repository.CrudRepository;

public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {
}
