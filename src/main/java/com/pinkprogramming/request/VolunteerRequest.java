package com.pinkprogramming.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerRequest {

    @Size(max = 20)
    @NotNull
    private String name;
    @Size(max = 20)
    @NotNull
    private  String lastName;
    @Size(max = 20)
    @NotNull
    private  String position;
}
