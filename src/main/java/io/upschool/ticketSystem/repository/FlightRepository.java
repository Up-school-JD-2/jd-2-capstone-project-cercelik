package io.upschool.ticketSystem.repository;

import io.upschool.ticketSystem.entity.Airport;
import io.upschool.ticketSystem.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findAllByFlightNumberIs(String flightNumber);

    List<Flight> findAllByIsActiveIs(boolean isActive);



    @Query(value = "SELECT fl FROM Flight fl WHERE  fl.originAirport.city = :originCity   AND  fl.destinationAirport.city = :destinationCity  ")
    List<Flight> findByRoute(@Param("originCity") String origin,@Param("destinationCity")  String destination);



}
