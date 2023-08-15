package io.upschool.ticketSystem.dto.airlineCompany;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirlineCompanySaveResponse {


    private Long id;

    private String name;

}
