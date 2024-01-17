package com.pinkprogramming.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerResponse {

    private String name;
    private  String lastName;
    private  String position;
}
