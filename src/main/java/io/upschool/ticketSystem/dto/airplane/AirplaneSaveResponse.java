package io.upschool.ticketSystem.dto.airplane;

import io.upschool.ticketSystem.dto.airlineCompany.AirlineCompanyGetResponse;
import io.upschool.ticketSystem.entity.AirlineCompany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirplaneSaveResponse {

    @NotBlank
    @NotEmpty
    @NotNull
    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull
    private String name;

    @NotBlank
    @NotEmpty
    @NotNull
    private Long capacity;

    @NotBlank
    @NotEmpty
    @NotNull
    private AirlineCompanyGetResponse airlineCompany;

}