package io.upschool.ticketSystem.dto.airlineCompany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineCompanyUpdateResponse {
    private Long id;

    private String name;
}
