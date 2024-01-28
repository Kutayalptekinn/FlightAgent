package coding.FlightSearch.API.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coding.FlightSearch.Business.Dtos.Flight.CreateFlightDto;
import coding.FlightSearch.Business.Dtos.Flight.ListFlightDto;
import coding.FlightSearch.Business.Dtos.Flight.SelectFlightDto;
import coding.FlightSearch.Business.Dtos.Flight.UpdateFlightDto;
import coding.FlightSearch.Business.Services.Flight.IFlightService;
import coding.FlightSearch.Entities.Entities.Flight;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/flights")

public class FlightsController {
	private IFlightService flightService;

	@Autowired
	public FlightsController(IFlightService flightService) {
		super();
		this.flightService = flightService;
	}
	

	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody @Valid CreateFlightDto createFlightDto) {
		SelectFlightDto result = this.flightService.createAsync(createFlightDto);
		if (result!=null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestBody @Valid SelectFlightDto selectFlightDto) {
		int id = selectFlightDto.getID();
		SelectFlightDto result=(SelectFlightDto) this.flightService.deleteAsync(id);
		if (result!=null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateFlightDto updateFlightDto) {
		SelectFlightDto result = this.flightService.updateAsync(updateFlightDto);
		if (result!=null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable @Valid int id) {
		SelectFlightDto result = this.flightService.GetAsync(id);
		if (result!=null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@GetMapping("getall")
	public ResponseEntity<?> getAll() {
		List<ListFlightDto> result = this.flightService.GetListAsync();
		if (!result.isEmpty()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
	
	@GetMapping("/search")
    public ResponseEntity<?> searchFlights(
            @RequestParam("departure") String departure,
            @RequestParam("destination") String destination,
            @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime departureDate,
            @RequestParam(value = "returnDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime returnDate
    ) {
        
		List<ListFlightDto> flights = flightService.SearchFlights(departure, destination, departureDate, returnDate);
        if(flights!=null) {
        	return ResponseEntity.ok(flights);
        }
        return ResponseEntity.badRequest().body(flights);
    }
    @Scheduled(cron = "0 0 1 * * ?") // Everyday 1 
    public void updateFlightData() {
    	LocalDateTime now = LocalDateTime.now();
        CreateFlightDto mockApiResponse = new CreateFlightDto();
        mockApiResponse.setArrivalAirport("istanbul");
        mockApiResponse.setDepartureAirport("kayseri");
        mockApiResponse.setDateOfDeparture(now);
        mockApiResponse.setDateOfReturn(now);
        mockApiResponse.setID(3);
        mockApiResponse.setPrice(12);
        
        flightService.createAsync(mockApiResponse);
    }
}
