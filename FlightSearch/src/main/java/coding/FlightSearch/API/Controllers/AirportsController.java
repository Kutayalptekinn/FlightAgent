package coding.FlightSearch.API.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coding.FlightSearch.Business.Dtos.Airport.CreateAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.ListAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.SelectAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.UpdateAirportDto;
import coding.FlightSearch.Business.Services.Airport.IAirportService;
import coding.FlightSearch.Entities.Entities.Airport;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/airports")
public class AirportsController {
	private IAirportService airportService;

	@Autowired
	public AirportsController(IAirportService airportService) {
		super();
		this.airportService = airportService;
	}
	

	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody @Valid CreateAirportDto createAirportDto) {
		SelectAirportDto result = this.airportService.createAsync(createAirportDto);
		if (result!=null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestBody @Valid SelectAirportDto selectAirportDto) {
		int id = selectAirportDto.getID();
		SelectAirportDto result=(SelectAirportDto) this.airportService.deleteAsync(id);
		if (result!=null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateAirportDto updateAirportDto) {
		SelectAirportDto result = this.airportService.updateAsync(updateAirportDto);
		if (result!=null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable @Valid int id) {
		SelectAirportDto result = this.airportService.GetAsync(id);
		if (result!=null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@GetMapping("getall")
	public ResponseEntity<?> getAll() {
		List<ListAirportDto> result = this.airportService.GetListAsync();
		if (!result.isEmpty()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
}
