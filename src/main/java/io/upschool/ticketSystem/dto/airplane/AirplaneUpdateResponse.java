package io.upschool.ticketSystem.dto.airplane;

import io.upschool.ticketSystem.dto.airlineCompany.AirlineCompanyGetResponse;
import io.upschool.ticketSystem.entity.AirlineCompany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AirplaneUpdateResponse {


    private Long id;



    private String name;


    private Long capacity;


    private AirlineCompanyGetResponse airlineCompany;
}
