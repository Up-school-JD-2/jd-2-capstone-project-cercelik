package io.upschool.ticketSystem.dto.ticket;

import io.upschool.ticketSystem.entity.Flight;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketSaveRequest {

    @NotNull
    @NotEmpty
    @NotBlank
    private String cardNumber;

    private long flightId;


}
