package io.upschool.ticketSystem.repository;

import io.upschool.ticketSystem.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findAllByNameIs(String name);

    List<Airport> findAllByCityIs(String city);





}
