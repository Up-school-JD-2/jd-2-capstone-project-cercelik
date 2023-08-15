package io.upschool.ticketSystem.controller;

import io.upschool.ticketSystem.dto.NotOkResponse;
import io.upschool.ticketSystem.dto.ticket.TicketGetResponse;
import io.upschool.ticketSystem.dto.ticket.TicketSaveRequest;
import io.upschool.ticketSystem.dto.ticket.TicketSaveResponse;
import io.upschool.ticketSystem.exception.CardNumberIsNotValidException;
import io.upschool.ticketSystem.exception.ResourceNotFoundException;
import io.upschool.ticketSystem.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketGetResponse>> getTickets() throws ResourceNotFoundException {
        var response = ticketService.getAllTickets();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PostMapping
    public ResponseEntity<Optional<TicketSaveResponse>> addTicket(@Valid @RequestBody TicketSaveRequest request, Errors errors) throws ResourceNotFoundException, CardNumberIsNotValidException {
        if (errors.hasErrors()) {
            return new ResponseEntity(new NotOkResponse(400, errors), HttpStatus.BAD_REQUEST);
        }

        var response = ticketService.save(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("search")
    public ResponseEntity<TicketGetResponse> searchTicket(String ticketNumber) throws ResourceNotFoundException {
        var response = ticketService.searchTicketsByNumber(ticketNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @DeleteMapping("{ticketNumber}")
    public void deleteTicket(@PathVariable String ticketNumber) {
        ticketService.cancelTicket(ticketNumber);
    }


}
