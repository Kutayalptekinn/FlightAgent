package coding.FlightSearch.Business.Services.Flight;

import java.time.LocalDateTime;
import java.util.List;

import coding.FlightSearch.Business.Dtos.Airport.CreateAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.ListAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.SelectAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.UpdateAirportDto;
import coding.FlightSearch.Business.Dtos.Flight.CreateFlightDto;
import coding.FlightSearch.Business.Dtos.Flight.ListFlightDto;
import coding.FlightSearch.Business.Dtos.Flight.SelectFlightDto;
import coding.FlightSearch.Business.Dtos.Flight.UpdateFlightDto;
import coding.FlightSearch.Core.Services.ICRUDAppService;
import coding.FlightSearch.Entities.Entities.Airport;

public interface IFlightService extends ICRUDAppService<SelectFlightDto, ListFlightDto, CreateFlightDto, UpdateFlightDto> {
	List<ListFlightDto> SearchFlights(String departure, String destination, LocalDateTime departureDate, LocalDateTime returnDate);
}
