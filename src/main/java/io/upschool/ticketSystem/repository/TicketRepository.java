package io.upschool.ticketSystem.repository;
import io.upschool.ticketSystem.entity.Airplane;
import io.upschool.ticketSystem.entity.Flight;
import io.upschool.ticketSystem.entity.Ticket;
import io.upschool.ticketSystem.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

   List<Ticket> findAllByStatusIs(TicketStatus status);



   Optional<Ticket> findByTicketNumberIs(String ticketNumber);

  /* koltuk sayısı ile
   aynı uçuş numarasına sahip biletlerin sayısı eğer eşit ise satın alınamaz hale gelmeli

   ticket üzerindeki flight id leri ticketın flight id sine eşitse count et
   @Query(value = “”select count(a) from Ticket a” +
           “”where a.fli)
*/


   @Query(value = "select count(a) from Ticket a " +
           "where a.flight.id = :flightId and a.status!='CANCELED'")
   int countSoldTickets(@Param("flightId") long flightId);


}
