package coding.FlightSearch.Entities.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int ID;
	@Column(name="departureAirport")
	private String departureAirport;
	@Column(name="arrivalAirport")
	private String arrivalAirport;
	@Column(name="dateOfDeparture")
	private LocalDateTime dateOfDeparture;
	@Column(name="dateOfReturn")
	private LocalDateTime dateOfReturn;
	@Column(name="price")
	private int price;
	
}
