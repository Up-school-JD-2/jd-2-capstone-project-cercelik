package io.upschool.ticketSystem.controller;


import io.upschool.ticketSystem.dto.NotOkResponse;
import io.upschool.ticketSystem.dto.airplane.*;
import io.upschool.ticketSystem.exception.ResourceNotFoundException;
import io.upschool.ticketSystem.service.AirplaneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/airplanes")
@RequiredArgsConstructor
public class AirplaneController {

    private final AirplaneService airplaneService;


    @GetMapping()
    public ResponseEntity<List<AirplaneGetResponse>> getAirplanes() throws ResourceNotFoundException {
        var response = airplaneService.getAllAirplanes();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<AirplaneGetResponse> getAirplaneById(@PathVariable long id) throws ResourceNotFoundException {

        var response = airplaneService.getAirplanesById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());

    }


    @GetMapping("search")
    public ResponseEntity<List<AirplaneGetResponse>> searchAirplane(String name) throws ResourceNotFoundException {
        var response = airplaneService.searchAirplaneByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<AirplaneSaveResponse> addAirplane(@Valid @RequestBody AirplaneSaveRequest request, Errors errors) throws ResourceNotFoundException {

        if (errors.hasErrors()) {
            return new ResponseEntity(new NotOkResponse(400, errors), HttpStatus.BAD_REQUEST);
        }
        var response = airplaneService.save(request);

        return ResponseEntity.status(HttpStatus.OK).body(response.get());

    }

    @PutMapping
    public ResponseEntity<AirplaneUpdateResponse> updateAirplane(@Valid @RequestBody AirplaneUpdateRequest request, Errors errors) throws ResourceNotFoundException {

        if (errors.hasErrors()) {
            return new ResponseEntity(new NotOkResponse(400, errors), HttpStatus.BAD_REQUEST);
        }
        var response = airplaneService.updateAirplane(request);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());

    }
}
