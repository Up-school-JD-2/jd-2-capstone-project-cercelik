package io.upschool.ticketSystem.dto.airplane;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class AirplaneSaveRequest {
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Name alanı minimum 2 maksimum 100 karater olabilir.")
    private String name;

    @Range(min = 2, max = 100, message = "capacity alanı minimum 2 maksimum 100 olabilir.")
    private long capacity;

    private long airlineCompanyID;
}
