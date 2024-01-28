package coding.FlightSearch.Business.Services.Airport;

import java.util.List;

import coding.FlightSearch.Business.Dtos.Airport.CreateAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.ListAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.SelectAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.UpdateAirportDto;
import coding.FlightSearch.Core.Services.ICRUDAppService;
import coding.FlightSearch.Entities.Entities.Airport;

public interface IAirportService extends ICRUDAppService<SelectAirportDto, ListAirportDto, CreateAirportDto, UpdateAirportDto> {
	
}
