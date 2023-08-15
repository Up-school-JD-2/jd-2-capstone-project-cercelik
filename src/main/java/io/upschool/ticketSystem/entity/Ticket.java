package io.upschool.ticketSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tickets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name= "ticket_Number", length=6)
        private String ticketNumber;


        @Enumerated(EnumType.STRING)
        @Column(name= "status", nullable=false, length=32)
        private TicketStatus status;

        @Column(name= "card_Number", length=16)
        private String cardNumber;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "flight_id", nullable = false)
        private Flight flight;

}
