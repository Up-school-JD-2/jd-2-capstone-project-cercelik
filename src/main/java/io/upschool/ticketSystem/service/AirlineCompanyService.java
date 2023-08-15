package io.upschool.ticketSystem.service;

import io.upschool.ticketSystem.dto.airlineCompany.*;
import io.upschool.ticketSystem.entity.AirlineCompany;
import io.upschool.ticketSystem.exception.ResourceNotFoundException;
import io.upschool.ticketSystem.repository.AirlineCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirlineCompanyService {

    private final AirlineCompanyRepository airlineCompanyRepository;

    public List<AirlineCompanyGetResponse> searchAirlineCompanyByName(String name) throws ResourceNotFoundException {

        var airlinesByName = airlineCompanyRepository
                .findAllByNameIs(name)
                .stream()
                .map(x -> new AirlineCompanyGetResponse(x.getId(), x.getName()))
                .collect(Collectors.toList());
        if (airlinesByName.size() == 0) {
            throw new ResourceNotFoundException();
        }
        return airlinesByName;

    }

    public AirlineCompanySaveResponse save(AirlineCompanySaveRequest airlineCompanySaveRequestDto) {
        var newAirlineCompany = AirlineCompany
                .builder()
                .name(airlineCompanySaveRequestDto.getName())
                .build();

        AirlineCompany savedAirlineCompany = airlineCompanyRepository.save(newAirlineCompany);
        return AirlineCompanySaveResponse
                .builder()
                .name(savedAirlineCompany.getName())
                .id(savedAirlineCompany.getId())
                .build();
    }

    public Optional<AirlineCompanyUpdateResponse> updateAirlineCompany(AirlineCompanyUpdateRequest request) throws ResourceNotFoundException {

        var optionalAirlineCompany = airlineCompanyRepository.findById(request.getId());

        if (optionalAirlineCompany.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        var airlineCompany = optionalAirlineCompany.get();
        airlineCompany.setName(request.getName());
        airlineCompany = airlineCompanyRepository.save(airlineCompany);

        var updateResponse = AirlineCompanyUpdateResponse
                .builder()
                .name(airlineCompany.getName())
                .id(airlineCompany.getId())
                .build();

        return Optional.of(updateResponse);

    }

    public List<AirlineCompanyGetResponse> getAllAirlineCompanies() throws ResourceNotFoundException {

        var airlineCompanies = airlineCompanyRepository.findAll();
        if (airlineCompanies.size() == 0) {
            throw new ResourceNotFoundException();
        }
        return airlineCompanies.stream().map(x -> new AirlineCompanyGetResponse(x.getId(), x.getName())).collect(Collectors.toList());
    }

    public Optional<AirlineCompanyGetResponse> getAirlineCompaniesById(long id) throws ResourceNotFoundException {

        Optional<AirlineCompany> airlineCompaniesById = airlineCompanyRepository.findById(id);
        if (airlineCompaniesById.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        var airlineCompany = airlineCompaniesById.get();
        var getResponse = AirlineCompanyGetResponse
                .builder()
                .id(airlineCompany.getId())
                .name(airlineCompany.getName())
                .build();
        return Optional.of(getResponse);
    }


}
