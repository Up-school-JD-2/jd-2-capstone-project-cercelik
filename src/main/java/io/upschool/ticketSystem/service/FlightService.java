package io.upschool.ticketSystem.service;

import io.upschool.ticketSystem.dto.airlineCompany.AirlineCompanyGetResponse;
import io.upschool.ticketSystem.dto.airplane.AirplaneGetResponse;
import io.upschool.ticketSystem.dto.airport.AirportGetResponse;
import io.upschool.ticketSystem.dto.flight.FlightGetResponse;
import io.upschool.ticketSystem.dto.flight.FlightSaveRequest;
import io.upschool.ticketSystem.dto.flight.FlightSaveResponse;
import io.upschool.ticketSystem.entity.Flight;
import io.upschool.ticketSystem.exception.ResourceNotFoundException;
import io.upschool.ticketSystem.repository.AirplaneRepository;
import io.upschool.ticketSystem.repository.AirportRepository;
import io.upschool.ticketSystem.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    private final AirplaneRepository airplaneRepository;

    public List<FlightGetResponse> getAllFlights() throws ResourceNotFoundException {
        var flights = flightRepository.findAllByIsActiveIs(true);
        if (flights.size() == 0) {
            throw new ResourceNotFoundException();
        }
        return flights.stream().map(x -> new FlightGetResponse(
                        x.getId(),
                        x.getFlightNumber(),
                        new AirportGetResponse(x.getOriginAirport().getId(),
                                x.getOriginAirport().getName(),
                                x.getOriginAirport().getCity()),
                        new AirportGetResponse(x.getDestinationAirport().getId(),
                                x.getDestinationAirport().getName(),
                                x.getDestinationAirport().getCity()),
                        new AirplaneGetResponse(x.getAirplane().getId(),
                                x.getAirplane().getName(),
                                x.getAirplane().getCapacity(),
                                new AirlineCompanyGetResponse(x.getAirplane().getAirlineCompany().getId(),
                                        x.getAirplane().getAirlineCompany().getName()))
                ))
                .collect(Collectors.toList());
    }


    public FlightSaveResponse save(FlightSaveRequest flightSaveRequestDto) {

        long originAirportId = flightSaveRequestDto.getOriginAirportId();
        var originAirport = airportRepository.findById(originAirportId);

        long destinationAirportId = flightSaveRequestDto.getDestinationAirportId();
        var destinationAirport = airportRepository.findById(destinationAirportId);

        long airplaneId = flightSaveRequestDto.getAirplaneId();
        var airplane = airplaneRepository.findById(airplaneId);

        var newFlight = Flight
                .builder()
                .flightNumber(flightSaveRequestDto.getFlightNumber())
                .originAirport(originAirport.get())
                .destinationAirport(destinationAirport.get())
                .airplane(airplane.get())
                .build();

        Flight savedFlight = flightRepository.save(newFlight);

        var originAirportDto = AirportGetResponse
                .builder()
                .id(savedFlight.getOriginAirport().getId())
                .name(savedFlight.getOriginAirport().getName())
                .city(savedFlight.getOriginAirport().getCity())
                .build();

        var destinationAirportDto = AirportGetResponse
                .builder()
                .id(savedFlight.getDestinationAirport().getId())
                .name(savedFlight.getDestinationAirport().getName())
                .city(savedFlight.getDestinationAirport().getCity())
                .build();

        var airlineDto = AirlineCompanyGetResponse
                .builder()
                .id(savedFlight.getAirplane().getAirlineCompany().getId())
                .name(savedFlight.getAirplane().getAirlineCompany().getName())
                .build();

        var airplaneDto = AirplaneGetResponse
                .builder()
                .id(savedFlight.getAirplane().getId())
                .name(savedFlight.getAirplane().getName())
                .capacity(savedFlight.getAirplane().getCapacity())
                .airlineCompany(airlineDto)
                .build();

        var response = FlightSaveResponse
                .builder()
                .id(savedFlight.getId())
                .flightNumber(savedFlight.getFlightNumber())
                .originAirport(originAirportDto)
                .destinationAirport(destinationAirportDto)
                .airplane(airplaneDto)
                .build();

        return response;

    }


    public List<FlightGetResponse> searchFlightsByRoute(String origin, String destination) throws ResourceNotFoundException {

        var flightsByRoute = flightRepository
                .findByRoute(origin, destination)
                .stream()
                .map(x -> new FlightGetResponse(
                        x.getId(),
                        x.getFlightNumber(),
                        new AirportGetResponse(x.getOriginAirport().getId(),
                                x.getOriginAirport().getName(),
                                x.getOriginAirport().getCity()),
                        new AirportGetResponse(x.getDestinationAirport().getId(),
                                x.getDestinationAirport().getName(),
                                x.getDestinationAirport().getCity()),
                        new AirplaneGetResponse(x.getAirplane().getId(),
                                x.getAirplane().getName(),
                                x.getAirplane().getCapacity(),
                                new AirlineCompanyGetResponse(x.getAirplane().getAirlineCompany().getId(),
                                        x.getAirplane().getAirlineCompany().getName()))
                ))
                .collect(Collectors.toList());

        if (flightsByRoute.size() == 0) {
            throw new ResourceNotFoundException();
        }

        return flightsByRoute;
    }

}




