
package io.upschool.ticketSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="airlineCompanies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AirlineCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name", nullable=false, length=50)
    private String name;



}

