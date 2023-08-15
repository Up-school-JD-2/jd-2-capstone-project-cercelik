package io.upschool.ticketSystem.service;

import io.upschool.ticketSystem.dto.airlineCompany.AirlineCompanyGetResponse;
import io.upschool.ticketSystem.dto.airplane.*;
import io.upschool.ticketSystem.entity.Airplane;
import io.upschool.ticketSystem.exception.ResourceNotFoundException;
import io.upschool.ticketSystem.repository.AirlineCompanyRepository;
import io.upschool.ticketSystem.repository.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final AirlineCompanyRepository airlineCompanyRepository;


    public List<AirplaneGetResponse> getAllAirplanes() throws ResourceNotFoundException {

        var airplanes = airplaneRepository.findAll();

        if (airplanes.size() == 0) {
            throw new ResourceNotFoundException();
        }
        return airplanes
                .stream()
                .map(x ->
                        new AirplaneGetResponse(
                                x.getId(),
                                x.getName(),
                                x.getCapacity(),
                                new AirlineCompanyGetResponse(
                                        x.getAirlineCompany().getId(),
                                        x.getAirlineCompany().getName())))
                .collect(Collectors.toList());
    }


    public Optional<AirplaneGetResponse> getAirplanesById(long id) throws ResourceNotFoundException {

        Optional<Airplane> airplaneById = airplaneRepository.findById(id);
        if (airplaneById.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        var airplane = airplaneById.get();
        var airlineCompanyDto = AirlineCompanyGetResponse
                .builder()
                .id(airplane.getAirlineCompany().getId())
                .name(airplane.getAirlineCompany().getName())
                .build();

        var getResponse = AirplaneGetResponse
                .builder()
                .id(airplane.getId())
                .name(airplane.getName())
                .capacity(airplane.getCapacity())
                .airlineCompany(airlineCompanyDto)
                .build();
        return Optional.of(getResponse);

    }

    public List<AirplaneGetResponse> searchAirplaneByName(String name) throws ResourceNotFoundException {

        var airplanesByName = airplaneRepository
                .findAllByNameIs(name)
                .stream()
                .map(x -> new AirplaneGetResponse(x.getId(),
                        x.getName(),
                        x.getCapacity(),
                        new AirlineCompanyGetResponse(
                                x.getAirlineCompany().getId(),
                                x.getAirlineCompany().getName())))
                .collect(Collectors.toList());

        if (airplanesByName.size() == 0) {
            throw new ResourceNotFoundException();
        }

        return airplanesByName;

    }

    public Optional<AirplaneSaveResponse> save(AirplaneSaveRequest airplaneSaveRequestDto) throws ResourceNotFoundException {


        long companyId = airplaneSaveRequestDto.getAirlineCompanyID();
        var airlineCompany = airlineCompanyRepository.findById(companyId);
        if (airlineCompany.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        var newAirplane = Airplane
                .builder()
                .name(airplaneSaveRequestDto.getName())
                .capacity(airplaneSaveRequestDto.getCapacity())
                .airlineCompany(airlineCompany.get())
                .build();

        Airplane savedAirplane = airplaneRepository.save(newAirplane);
        var airlineCompanyDto = AirlineCompanyGetResponse
                .builder()
                .id(savedAirplane.getAirlineCompany().getId())
                .name(savedAirplane.getAirlineCompany().getName())
                .build();

        var response = AirplaneSaveResponse
                .builder()
                .id(savedAirplane.getId())
                .name(savedAirplane.getName())
                .capacity(savedAirplane.getCapacity())
                .airlineCompany(airlineCompanyDto)
                .build();
        return Optional.of(response);

    }


    public Optional<AirplaneUpdateResponse> updateAirplane(AirplaneUpdateRequest airplaneUpdateRequestDto) throws ResourceNotFoundException {

        long companyId = airplaneUpdateRequestDto.getAirlineCompanyID();
        var airlineCompany = airlineCompanyRepository.findById(companyId);
        var optionalAirplane = airplaneRepository.findById(airplaneUpdateRequestDto.getId());
        if (airlineCompany.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        var airplane = optionalAirplane.get();
        airplane.setName(airplaneUpdateRequestDto.getName());
        airplane.setCapacity(airplaneUpdateRequestDto.getCapacity());
        airplane.setAirlineCompany(airlineCompany.get());
        airplane = airplaneRepository.save(airplane);

        var airlineCompanyDto = AirlineCompanyGetResponse
                .builder()
                .id(airplane.getAirlineCompany().getId())
                .name(airplane.getAirlineCompany().getName())
                .build();

        var updateResponse = AirplaneUpdateResponse
                .builder()
                .id(airplane.getId())
                .name(airplane.getName())
                .capacity(airplane.getCapacity())
                .airlineCompany(airlineCompanyDto)
                .build();
        return Optional.of(updateResponse);


    }
}



