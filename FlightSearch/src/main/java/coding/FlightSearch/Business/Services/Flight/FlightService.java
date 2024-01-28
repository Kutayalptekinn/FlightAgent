package coding.FlightSearch.Business.Services.Flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coding.FlightSearch.Business.Dtos.Flight.CreateFlightDto;
import coding.FlightSearch.Business.Dtos.Flight.SelectFlightDto;
import coding.FlightSearch.Business.Dtos.Flight.UpdateFlightDto;
import coding.FlightSearch.Business.Dtos.Flight.ListFlightDto;
import coding.FlightSearch.Core.Utilities.Mappers.IModelMapperService;
import coding.FlightSearch.DataAccess.Repositories.Flight.IFlightRepository;
import coding.FlightSearch.Entities.Entities.Flight;
@Service
public class FlightService implements IFlightService {
	private IFlightRepository flightRepository;
	private IModelMapperService modelMapperService;
	@Autowired
	public FlightService(IFlightRepository flightRepository) {
		super();
		this.flightRepository = flightRepository;
	}


	@Override
	public SelectFlightDto createAsync(CreateFlightDto input) {
		Flight flight = this.modelMapperService.forResponse().map(input, Flight.class);
		this.flightRepository.save(flight);
		SelectFlightDto selectFlightDto = this.modelMapperService.forResponse().map(flight,
				SelectFlightDto.class);
		return selectFlightDto;
	}

	@Override
	public SelectFlightDto updateAsync(UpdateFlightDto input) {
		
		Flight flight = this.modelMapperService.forRequest().map(input, Flight.class);
		this.flightRepository.save(flight);
		SelectFlightDto selectFlightDto = this.modelMapperService.forResponse().map(flight,
				SelectFlightDto.class);

		return selectFlightDto;
	}

	@Override
	public SelectFlightDto deleteAsync(int id) {
		var entity=this.flightRepository.getReferenceById(id);
				this.flightRepository.deleteById(id);
				SelectFlightDto selectFlightDto = this.modelMapperService.forResponse().map(entity,
						SelectFlightDto.class);
			return selectFlightDto;
		
			
	}

	@Override
	public SelectFlightDto GetAsync(int id) {
		Optional<Flight> flight = this.flightRepository.findById(id);
		SelectFlightDto selectFlightDto = this.modelMapperService.forResponse().map(flight, SelectFlightDto.class);
		return selectFlightDto;
	}

	@Override
	public List<ListFlightDto> GetListAsync() {
		var flightsList= flightRepository.findAll();
		List<ListFlightDto> listFlightDto = flightsList.stream()
				.map(x->this.modelMapperService.forResponse().map(x, ListFlightDto.class)).collect(Collectors.toList());
		return listFlightDto;
	}
	
    public List<ListFlightDto> SearchFlights(String departure, String destination, LocalDateTime departureDate, LocalDateTime returnDate) {

        List<Flight> flights = new ArrayList<Flight>();

        if (returnDate != null) {
           
        	flights = flightRepository.findRoundTripFlights(departure, destination, departureDate, returnDate);

        } else {
            
        	flights = flightRepository.findOneWayFlights(departure, destination, departureDate);

        }
        List<ListFlightDto> listFlightDto = flights.stream()
				.map(x->this.modelMapperService.forResponse().map(x, ListFlightDto.class)).collect(Collectors.toList());

        return listFlightDto;
    }
}
