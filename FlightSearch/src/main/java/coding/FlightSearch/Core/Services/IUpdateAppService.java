package coding.FlightSearch.Core.Services;


public interface IUpdateAppService<TGetOutputDto, TUpdateInput> {
	TGetOutputDto updateAsync(TUpdateInput input);
}
