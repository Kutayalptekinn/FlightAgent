package coding.FlightSearch.Core.Services;

public interface ICRUDAppService<TGetOutputDto, TGetListOutputDto, TCreateInput, TUpdateInput>
extends ICreateAppService<TGetOutputDto, TCreateInput>,
        IUpdateAppService<TGetOutputDto, TUpdateInput>,
        IDeleteAppService,
        IReadOnlyAppService<TGetOutputDto, TGetListOutputDto> {

}
