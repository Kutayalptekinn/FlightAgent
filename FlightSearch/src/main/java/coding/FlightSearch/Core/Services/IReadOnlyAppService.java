package coding.FlightSearch.Core.Services;

import java.util.List;

public interface IReadOnlyAppService<TGetOutputDto, TGetListOutputDto> {
	TGetOutputDto GetAsync(int id);

	List<TGetListOutputDto> GetListAsync();
}
