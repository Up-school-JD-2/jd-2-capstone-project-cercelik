package io.upschool.ticketSystem.dto.airplane;

import io.upschool.ticketSystem.dto.airlineCompany.AirlineCompanyGetResponse;
import io.upschool.ticketSystem.entity.AirlineCompany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneGetResponse {
    private long id;

    private String name;
    private long capacity;

    private AirlineCompanyGetResponse airlineCompany;

}