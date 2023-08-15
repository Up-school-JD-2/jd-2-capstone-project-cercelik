package io.upschool.ticketSystem.controller;
import io.upschool.ticketSystem.dto.NotOkResponse;
import io.upschool.ticketSystem.dto.airplane.AirplaneGetResponse;
import io.upschool.ticketSystem.dto.airplane.AirplaneSaveRequest;
import io.upschool.ticketSystem.dto.airplane.AirplaneSaveResponse;
import io.upschool.ticketSystem.dto.flight.FlightGetResponse;
import io.upschool.ticketSystem.dto.flight.FlightSaveRequest;
import io.upschool.ticketSystem.dto.flight.FlightSaveResponse;
import io.upschool.ticketSystem.exception.ResourceNotFoundException;
import io.upschool.ticketSystem.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightGetResponse>> getAirplanes() throws ResourceNotFoundException {
        var response = flightService.getAllFlights();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PostMapping
    public ResponseEntity<FlightSaveResponse> addAirline(@Valid @RequestBody FlightSaveRequest request, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity(new NotOkResponse(400, errors), HttpStatus.BAD_REQUEST);
        }

        var response = flightService.save(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("search")
    public ResponseEntity<List<FlightGetResponse>> searchFlight(String origin, String destination) throws ResourceNotFoundException {
        var response = flightService.searchFlightsByRoute( origin,  destination);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
