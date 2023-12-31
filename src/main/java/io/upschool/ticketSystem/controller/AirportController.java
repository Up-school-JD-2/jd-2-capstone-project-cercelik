package io.upschool.ticketSystem.controller;

import io.upschool.ticketSystem.dto.NotOkResponse;
import io.upschool.ticketSystem.dto.airport.*;
import io.upschool.ticketSystem.exception.ResourceNotFoundException;
import io.upschool.ticketSystem.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {


    private final AirportService airportService;

    @PostMapping()
    public ResponseEntity<AirportSaveResponse> addAirport(@Valid @RequestBody AirportSaveRequest request, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity(new NotOkResponse(400, errors), HttpStatus.BAD_REQUEST);
        }

        var response = airportService.save(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping()
    public ResponseEntity<List<AirportGetResponse>> getAirports() throws ResourceNotFoundException {
        var response = airportService.getAllAirports();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<AirportGetResponse> getAirportById(@PathVariable long id) throws ResourceNotFoundException {

        var response = airportService.getAirportById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PutMapping()
    public ResponseEntity<AirportUpdateResponse> updateAirport(@Valid @RequestBody AirportUpdateRequest updateRequest, Errors errors) throws ResourceNotFoundException {

        if (errors.hasErrors()) {
            return new ResponseEntity(new NotOkResponse(400, errors), HttpStatus.BAD_REQUEST);
        }

        var response = airportService.updateAirport(updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());

    }

    @GetMapping("search")
    public ResponseEntity<List<AirportGetResponse>> searchAirport(String city) throws ResourceNotFoundException {
        var response = airportService.searchAirportByCity(city);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
