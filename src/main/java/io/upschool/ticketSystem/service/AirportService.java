package io.upschool.ticketSystem.service;

import io.upschool.ticketSystem.dto.airport.*;
import io.upschool.ticketSystem.entity.Airport;
import io.upschool.ticketSystem.exception.ResourceNotFoundException;
import io.upschool.ticketSystem.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<AirportGetResponse> searchAirportByCity(String city) throws ResourceNotFoundException {

        var airportsByName = airportRepository
                .findAllByCityIs(city)
                .stream()
                .map(x -> new AirportGetResponse(x.getId(), x.getName(), x.getCity()))
                .collect(Collectors.toList());
        if (airportsByName.size() == 0) {
            throw new ResourceNotFoundException();
        }
        return airportsByName;

    }

    public AirportSaveResponse save(AirportSaveRequest airportSaveRequestDto) {
        var newAirport = Airport
                .builder()
                .name(airportSaveRequestDto.getName())
                .city(airportSaveRequestDto.getCity())
                .build();

        Airport savedAirport = airportRepository.save(newAirport);
        return AirportSaveResponse
                .builder()
                .name(savedAirport.getName())
                .city(savedAirport.getCity())
                .id(savedAirport.getId())
                .build();
    }

    public Optional<AirportUpdateResponse> updateAirport(AirportUpdateRequest request) throws ResourceNotFoundException {

        var optionalAirport = airportRepository.findById(request.getId());
        if (optionalAirport.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        var airport = optionalAirport.get();
        airport.setName(request.getName());
        airport = airportRepository.save(airport);

        var updateResponse = AirportUpdateResponse
                .builder()
                .name(airport.getName())
                .id(airport.getId())
                .build();


        return Optional.of(updateResponse);
    }

    public List<AirportGetResponse> getAllAirports() throws ResourceNotFoundException {
        var airports = airportRepository.findAll();
        if (airports.size() == 0) {
            throw new ResourceNotFoundException();
        }
        return airports.stream().map(x -> new AirportGetResponse(x.getId(), x.getName(), x.getCity()))
                .collect(Collectors.toList());

    }

    public Optional<AirportGetResponse> getAirportById(long id) throws ResourceNotFoundException {

        Optional<Airport> airportsById = airportRepository.findById(id);

        if (airportsById.isEmpty()) {
            throw new ResourceNotFoundException();
        }


        var airport = airportsById.get();
        var getResponse = AirportGetResponse
                .builder()
                .id(airport.getId())
                .name(airport.getName())
                .city(airport.getCity())
                .build();
        return Optional.of(getResponse);

    }


}
