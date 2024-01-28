package coding.FlightSearch.Core.Services;



public interface ICreateAppService<TGetOutputDto, TCreateInput>
{
	TGetOutputDto createAsync(TCreateInput input);
}
