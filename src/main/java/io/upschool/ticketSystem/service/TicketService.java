package io.upschool.ticketSystem.service;

import io.upschool.ticketSystem.dto.airlineCompany.AirlineCompanyGetResponse;
import io.upschool.ticketSystem.dto.airplane.AirplaneGetResponse;
import io.upschool.ticketSystem.dto.airport.AirportGetResponse;
import io.upschool.ticketSystem.dto.flight.FlightGetResponse;
import io.upschool.ticketSystem.dto.ticket.TicketGetResponse;
import io.upschool.ticketSystem.dto.ticket.TicketSaveRequest;
import io.upschool.ticketSystem.dto.ticket.TicketSaveResponse;
import io.upschool.ticketSystem.entity.Ticket;
import io.upschool.ticketSystem.entity.TicketStatus;
import io.upschool.ticketSystem.exception.CardNumberIsNotValidException;
import io.upschool.ticketSystem.exception.ResourceNotFoundException;
import io.upschool.ticketSystem.repository.FlightRepository;
import io.upschool.ticketSystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TicketService {
    static ArrayList<Integer> list = null;
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    public static int generateTicketNumber() {

        Random rand = new Random();
        var result = rand.nextInt(10000, 99999);
        return result;
    }

    public List<TicketGetResponse> getAllTickets() throws ResourceNotFoundException {
        var tickets = ticketRepository.findAllByStatusIs(TicketStatus.SOLD);
        if (tickets.size() == 0) {
            throw new ResourceNotFoundException();
        }
        if (tickets != null) {
            return tickets.stream().map(x -> new TicketGetResponse(
                                    x.getId(),
                                    x.getTicketNumber(),
                                    x.getCardNumber(),
                                    new FlightGetResponse(
                                            x.getFlight().getId(),
                                            x.getFlight().getFlightNumber(),
                                            new AirportGetResponse(x.getFlight().getOriginAirport().getId(),
                                                    x.getFlight().getOriginAirport().getName(),
                                                    x.getFlight().getOriginAirport().getCity()),
                                            new AirportGetResponse(x.getFlight().getDestinationAirport().getId(),
                                                    x.getFlight().getDestinationAirport().getName(),
                                                    x.getFlight().getDestinationAirport().getCity()),
                                            new AirplaneGetResponse(x.getFlight().getAirplane().getId(),
                                                    x.getFlight().getAirplane().getName(),
                                                    x.getFlight().getAirplane().getCapacity(),
                                                    new AirlineCompanyGetResponse(x.getFlight().getAirplane().getAirlineCompany().getId(),
                                                            x.getFlight().getAirplane().getAirlineCompany().getName()))
                                    )
                            )
                    )
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Optional<TicketSaveResponse> save(TicketSaveRequest ticketSaveRequestDto) throws ResourceNotFoundException, CardNumberIsNotValidException {
        long flightId = ticketSaveRequestDto.getFlightId();
        var flight = flightRepository.findById(flightId);
        var ticketNumber = String.valueOf(generateTicketNumber());
        var maskedCardNumber = maskedCardNumber(ticketSaveRequestDto.getCardNumber());

        long capacity = flight.get().getAirplane().getCapacity();
        long soldSeatCount = ticketRepository.countSoldTickets(flightId);


        if (capacity == soldSeatCount) {
            throw new ResourceNotFoundException();
        }

        var newTicket = Ticket
                .builder()
                .cardNumber(ticketSaveRequestDto.getCardNumber())
                .ticketNumber(ticketNumber)
                .cardNumber(maskedCardNumber)
                .status(TicketStatus.SOLD)
                .flight(flight.get())
                .build();
        Ticket savedTicket = ticketRepository.save(newTicket);

        var originAirportDto = AirportGetResponse
                .builder()
                .id(savedTicket.getFlight().getOriginAirport().getId())
                .name(savedTicket.getFlight().getOriginAirport().getName())
                .city(savedTicket.getFlight().getOriginAirport().getCity())
                .build();

        var destinationAirportDto = AirportGetResponse
                .builder()
                .id(savedTicket.getFlight().getDestinationAirport().getId())
                .name(savedTicket.getFlight().getDestinationAirport().getName())
                .city(savedTicket.getFlight().getDestinationAirport().getCity())
                .build();

        var airlineDto = AirlineCompanyGetResponse
                .builder()
                .id(savedTicket.getFlight().getAirplane().getAirlineCompany().getId())
                .name(savedTicket.getFlight().getAirplane().getAirlineCompany().getName())
                .build();

        var airplaneDto = AirplaneGetResponse
                .builder()
                .id(savedTicket.getFlight().getAirplane().getId())
                .name(savedTicket.getFlight().getAirplane().getName())
                .capacity(savedTicket.getFlight().getAirplane().getCapacity())
                .airlineCompany(airlineDto)
                .build();

        var flightDto = FlightGetResponse
                .builder()
                .id(savedTicket.getFlight().getId())
                .flightNumber(savedTicket.getFlight().getFlightNumber())
                .originAirport(originAirportDto)
                .destinationAirport(destinationAirportDto)
                .airplane(airplaneDto)
                .build();


        var response = TicketSaveResponse
                .builder()
                .ticketNumber(savedTicket.getTicketNumber())
                .cardNumber(savedTicket.getCardNumber())
                .flight(flightDto)
                .build();

        return Optional.of(response);

    }

    public String maskedCardNumber(String cardNumber) throws CardNumberIsNotValidException {

        if (cardNumber == null || cardNumber.length() > 20 || cardNumber.length() < 16) {
            throw new CardNumberIsNotValidException();
        }


        var maskedCardNumber = cardNumber
                .trim()
                .replace(" ", "")
                .replace("-", "")
                .replace("(", "")
                .replace(")", "")
                .replace(",", "");


        if (maskedCardNumber.length() != 16) {
            throw new CardNumberIsNotValidException();
        }

        var prefix = maskedCardNumber.substring(0, 6);

        var suffix = maskedCardNumber.substring(maskedCardNumber.length() - 4);

        var mask = "******";

        maskedCardNumber = prefix.concat(mask).concat(suffix);

        return maskedCardNumber;

    }


    public Optional<TicketGetResponse> searchTicketsByNumber(String ticketNumber) throws ResourceNotFoundException {


        var optionalTicket = ticketRepository
                .findByTicketNumberIs(ticketNumber);

        if (optionalTicket.isEmpty() && optionalTicket.get().getStatus().equals(TicketStatus.CANCELED)) {
            throw new ResourceNotFoundException();
        }

        var ticket = optionalTicket.get();
        var response = new TicketGetResponse(
                ticket.getId(),
                ticket.getTicketNumber(),
                ticket.getCardNumber(),
                new FlightGetResponse(
                        ticket.getFlight().getId(),
                        ticket.getFlight().getFlightNumber(),
                        new AirportGetResponse(ticket.getFlight().getOriginAirport().getId(),
                                ticket.getFlight().getOriginAirport().getName(),
                                ticket.getFlight().getOriginAirport().getCity()),
                        new AirportGetResponse(ticket.getFlight().getDestinationAirport().getId(),
                                ticket.getFlight().getDestinationAirport().getName(),
                                ticket.getFlight().getDestinationAirport().getCity()),
                        new AirplaneGetResponse(ticket.getFlight().getAirplane().getId(),
                                ticket.getFlight().getAirplane().getName(),
                                ticket.getFlight().getAirplane().getCapacity(),
                                new AirlineCompanyGetResponse(ticket.getFlight().getAirplane().getAirlineCompany().getId(),
                                        ticket.getFlight().getAirplane().getAirlineCompany().getName()))
                )

        );

        return Optional.of(response);
    }


    public void cancelTicket(String ticketNumber) {
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketNumberIs(ticketNumber);

        if (optionalTicket.isPresent()) {
            var ticket = optionalTicket.get();
            ticket.setStatus(TicketStatus.CANCELED);
            ticketRepository.save(ticket);
        }

    }


}
