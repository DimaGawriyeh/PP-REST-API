package com.pinkprogramming.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendeeRequest {

    @Size(max = 20)
    @NotNull
    @Schema(title = "name", example = "name", description = "Attendee's first name")
    private String name;
    @Size(max = 20)
    @NotNull
    @Schema(title = "lastName", example = "last name", description = "Attendee's last name")
    private  String lastName;
}
