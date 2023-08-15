package io.upschool.ticketSystem.dto.airport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportGetResponse {
    private Long id;

    private String name;

    private String city;
}