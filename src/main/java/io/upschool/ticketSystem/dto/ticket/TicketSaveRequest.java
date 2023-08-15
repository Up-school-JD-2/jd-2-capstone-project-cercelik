package io.upschool.ticketSystem.dto.ticket;

import io.upschool.ticketSystem.entity.Flight;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 16, max = 20,message = "cardNumber alanı minimum 16 maksimum 20 karater olabilir.")
    private String cardNumber;

    private long flightId;


}
