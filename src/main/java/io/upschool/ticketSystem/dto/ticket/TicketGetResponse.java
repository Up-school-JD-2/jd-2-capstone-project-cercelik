package io.upschool.ticketSystem.dto.ticket;

import io.upschool.ticketSystem.dto.flight.FlightGetResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketGetResponse {
    private Long id;

    private String ticketNumber;

    private String cardNumber;

    private FlightGetResponse flightId;


}
