package coding.FlightSearch.Business.Services.Airport;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coding.FlightSearch.Business.Dtos.Airport.CreateAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.SelectAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.UpdateAirportDto;
import coding.FlightSearch.Business.Dtos.Airport.ListAirportDto;
import coding.FlightSearch.Core.Utilities.Mappers.IModelMapperService;
import coding.FlightSearch.DataAccess.Repositories.Airport.IAirportRepository;
import coding.FlightSearch.Entities.Entities.Airport;
@Service
public class AirportService implements IAirportService {
	private IAirportRepository airportRepository;
	private IModelMapperService modelMapperService;
	@Autowired
	public AirportService(IAirportRepository airportRepository) {
		super();
		this.airportRepository = airportRepository;
	}


	@Override
	public SelectAirportDto createAsync(CreateAirportDto input) {
		Airport airport = this.modelMapperService.forResponse().map(input, Airport.class);
		this.airportRepository.save(airport);
		SelectAirportDto selectAirportDto = this.modelMapperService.forResponse().map(airport,
				SelectAirportDto.class);
		return selectAirportDto;
	}

	@Override
	public SelectAirportDto updateAsync(UpdateAirportDto input) {
		
		Airport airport = this.modelMapperService.forRequest().map(input, Airport.class);
		this.airportRepository.save(airport);
		SelectAirportDto selectAirportDto = this.modelMapperService.forResponse().map(airport,
				SelectAirportDto.class);

		return selectAirportDto;
	}

	@Override
	public SelectAirportDto deleteAsync(int id) {
		var entity=this.airportRepository.getReferenceById(id);
				this.airportRepository.deleteById(id);
				SelectAirportDto selectAirportDto = this.modelMapperService.forResponse().map(entity,
						SelectAirportDto.class);
			return selectAirportDto;
		
			
	}

	@Override
	public SelectAirportDto GetAsync(int id) {
		Optional<Airport> airport = this.airportRepository.findById(id);
		SelectAirportDto selectAirportDto = this.modelMapperService.forResponse().map(airport, SelectAirportDto.class);
		return selectAirportDto;
	}

	@Override
	public List<ListAirportDto> GetListAsync() {
		var airportsList= airportRepository.findAll();
		List<ListAirportDto> listAirportDto = airportsList.stream()
				.map(x->this.modelMapperService.forResponse().map(x, ListAirportDto.class)).collect(Collectors.toList());
		return listAirportDto;
	}
}
