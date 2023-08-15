package io.upschool.ticketSystem.dto.airport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AirportUpdateRequest {

    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull
    private String name;
}
