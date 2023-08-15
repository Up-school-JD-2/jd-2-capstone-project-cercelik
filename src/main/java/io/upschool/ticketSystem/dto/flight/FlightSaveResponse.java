package io.upschool.ticketSystem.dto.flight;

import io.upschool.ticketSystem.dto.airplane.AirplaneGetResponse;
import io.upschool.ticketSystem.dto.airport.AirportGetResponse;
import io.upschool.ticketSystem.entity.Airplane;
import io.upschool.ticketSystem.entity.Airport;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightSaveResponse {

    private Long id;

    private String flightNumber;

    private AirportGetResponse originAirport;

    private AirportGetResponse destinationAirport;

    private AirplaneGetResponse airplane;

}
