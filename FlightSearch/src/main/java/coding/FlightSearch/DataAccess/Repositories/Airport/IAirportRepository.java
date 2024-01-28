package coding.FlightSearch.DataAccess.Repositories.Airport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coding.FlightSearch.Entities.Entities.Airport;


@Repository
public interface IAirportRepository extends JpaRepository<Airport,Integer> {
	
}
