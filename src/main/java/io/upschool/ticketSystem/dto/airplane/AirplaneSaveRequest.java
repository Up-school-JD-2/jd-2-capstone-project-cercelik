package io.upschool.ticketSystem.dto.airplane;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AirplaneSaveRequest {
    @NotBlank
    @NotEmpty
    @NotNull
    private String name;

    private long capacity;

    private long airlineCompanyID;
}
