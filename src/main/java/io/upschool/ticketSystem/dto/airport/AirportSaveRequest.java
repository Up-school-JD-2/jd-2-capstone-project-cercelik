package io.upschool.ticketSystem.dto.airport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AirportSaveRequest {

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100,message = "Name alanı minimum 2 maksimum 100 karater olabilir.")
    private String name;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100,message = "City alanı minimum 2 maksimum 100 karater olabilir.")
    private String city;
}
