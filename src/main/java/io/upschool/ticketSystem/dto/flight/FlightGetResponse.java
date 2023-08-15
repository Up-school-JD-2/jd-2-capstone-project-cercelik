package io.upschool.ticketSystem.dto.flight;
import io.upschool.ticketSystem.dto.airplane.AirplaneGetResponse;
import io.upschool.ticketSystem.dto.airport.AirportGetResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class FlightGetResponse {
        private long id;

        private String flightNumber;
        private AirportGetResponse originAirport;

        private AirportGetResponse destinationAirport;

        private AirplaneGetResponse airplane;

    }

