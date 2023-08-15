
package io.upschool.ticketSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="airports")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length=100)
    private String name;

    @Column(name="city", nullable = false, length=100)
    private String city;

}

