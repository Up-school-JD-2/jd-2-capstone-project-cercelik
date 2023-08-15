package io.upschool.ticketSystem.controller;

import io.upschool.ticketSystem.dto.NotOkResponse;
import io.upschool.ticketSystem.dto.airlineCompany.*;
import io.upschool.ticketSystem.exception.ResourceNotFoundException;
import io.upschool.ticketSystem.service.AirlineCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airline-companies")
@RequiredArgsConstructor
public class AirlineCompanyController {

    private final AirlineCompanyService airlineCompanyService;


    @PostMapping()
    public ResponseEntity<AirlineCompanySaveResponse> addAirlineCompany(@Valid @RequestBody AirlineCompanySaveRequest request, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity(new NotOkResponse(400, errors), HttpStatus.BAD_REQUEST);
        }
        var response = airlineCompanyService.save(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping()
    public ResponseEntity<List<AirlineCompanyGetResponse>> getAirlineCompanies() throws ResourceNotFoundException {
        var response = airlineCompanyService.getAllAirlineCompanies();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AirlineCompanyGetResponse> getAirlineCompanyById(@PathVariable long id) throws ResourceNotFoundException {

        var response = airlineCompanyService.getAirlineCompaniesById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PutMapping()
    public ResponseEntity<AirlineCompanyUpdateResponse> updateAirlineCompany(@Valid @RequestBody AirlineCompanyUpdateRequest updateRequest, Errors errors) throws ResourceNotFoundException {
        if (errors.hasErrors()) {
            return new ResponseEntity(new NotOkResponse(400, errors), HttpStatus.BAD_REQUEST);
        }
        var response = airlineCompanyService.updateAirlineCompany(updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("search")
    public ResponseEntity<List<AirlineCompanyGetResponse>> searchAirline(String name) throws ResourceNotFoundException {
        var response = airlineCompanyService.searchAirlineCompanyByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
