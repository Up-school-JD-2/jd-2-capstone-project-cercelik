package io.upschool.ticketSystem.dto.airlineCompany;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AirlineCompanyUpdateRequest {

    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull
    private String name;
}
