package telran.currency.dto;

import java.util.HashMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class CurrencyDto {

	boolean success;
	long timestamp;
	String base;
	String date;
	HashMap<String, Double> rates;

}
