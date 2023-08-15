package io.upschool.ticketSystem.repository;
import io.upschool.ticketSystem.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    List<Airplane> findAllByNameIs(String name);

}
