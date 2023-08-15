
package io.upschool.ticketSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="airplanes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length=25)
    private String name;

    @Column(name= "capacity", nullable=false)
    @Size(min=180, max=800)
    private long capacity;


    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private AirlineCompany airlineCompany;


}

