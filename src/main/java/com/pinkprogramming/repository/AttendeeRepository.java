package com.pinkprogramming.repository;

import com.pinkprogramming.entity.Attendee;
import org.springframework.data.repository.CrudRepository;

public interface AttendeeRepository extends CrudRepository<Attendee, Long> {
}
