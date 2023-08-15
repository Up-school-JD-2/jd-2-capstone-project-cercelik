package io.upschool.ticketSystem.dto.airlineCompany;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AirlineCompanySaveRequest {

    @NotBlank
    @NotEmpty
    @NotNull
    private String name;
}
