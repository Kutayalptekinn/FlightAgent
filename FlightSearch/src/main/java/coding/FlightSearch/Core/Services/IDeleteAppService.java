package coding.FlightSearch.Core.Services;


public interface IDeleteAppService<TGetOutputDto> {
	TGetOutputDto deleteAsync(int id);
}
