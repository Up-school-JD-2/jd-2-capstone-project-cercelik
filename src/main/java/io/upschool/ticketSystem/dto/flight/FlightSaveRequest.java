package io.upschool.ticketSystem.dto.flight;

import io.upschool.ticketSystem.entity.Airplane;
import io.upschool.ticketSystem.entity.Airport;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class FlightSaveRequest {

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100,message = "flightNumber alanı minimum 2 maksimum 100 karater olabilir.")
    private String flightNumber;


    private long originAirportId;

    private long destinationAirportId;


    private long airplaneId;


}
