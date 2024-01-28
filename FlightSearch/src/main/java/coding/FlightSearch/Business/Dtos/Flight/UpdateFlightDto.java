package coding.FlightSearch.Business.Dtos.Flight;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFlightDto {
	private int ID;
	private String departureAirport;
	private String arrivalAirport;
	private LocalDateTime dateOfDeparture;
	private LocalDateTime dateOfReturn;
	private int price;
}
