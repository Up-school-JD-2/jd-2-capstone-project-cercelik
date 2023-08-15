package io.upschool.ticketSystem.dto.airport;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportSaveResponse {


    private Long id;

    private String name;

    private String city;

}
