package coding.FlightSearch.DataAccess.Repositories.Flight;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import coding.FlightSearch.Entities.Entities.Airport;
import coding.FlightSearch.Entities.Entities.Flight;


@Repository
public interface IFlightRepository extends JpaRepository<Flight,Integer> {
	@Query("SELECT f FROM Flight f WHERE f.departure = :departure AND f.destination = :destination AND f.departureDate >= :departureDate AND f.returnDate <= :returnDate")
    List<Flight> findRoundTripFlights(@Param("departure") String departure, @Param("destination") String destination, @Param("departureDate") LocalDateTime departureDate, @Param("returnDate") LocalDateTime returnDate);

    @Query("SELECT f FROM Flight f WHERE f.departure = :departure AND f.destination = :destination AND f.departureDate >= :departureDate")
    List<Flight> findOneWayFlights(@Param("departure") String departure, @Param("destination") String destination, @Param("departureDate") LocalDateTime departureDate);
}
