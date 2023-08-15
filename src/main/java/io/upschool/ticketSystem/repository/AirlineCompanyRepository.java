package io.upschool.ticketSystem.repository;
import io.upschool.ticketSystem.entity.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Long> {

    List<AirlineCompany> findAllByNameIs(String name);


}
