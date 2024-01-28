package coding.FlightSearch.Business.Dtos.Airport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectAirportDto {
	private int ID;
	private String city;
}