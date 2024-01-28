package coding.FlightSearch.Core.Utilities.Mappers;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
	ModelMapper forRequest();
	ModelMapper forResponse(); 	
}
