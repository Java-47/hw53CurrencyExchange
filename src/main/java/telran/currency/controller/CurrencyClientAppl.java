package telran.currency.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import telran.currency.dto.CurrencyDto;

public class CurrencyClientAppl {

	private static final String API_KEY = "4bbbee24c73504eeab5096340d25443b";

	public static void main(String[] args) throws URISyntaxException, IOException {

		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<String> requestEntity = new RequestEntity<String>(HttpMethod.GET,
				new URI("http://data.fixer.io/api/latest?access_key=" + API_KEY));
		ResponseEntity<CurrencyDto> responseEntity = restTemplate.exchange(requestEntity, CurrencyDto.class);
		HashMap<String, Double> rates = responseEntity.getBody().getRates();

		allCurrenciesPrint(rates);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String currencyFrom = readCurrency("Please enter a currency FROM convert: ", reader);
		String currencyTo = readCurrency("Please enter a currency TO convert: ", reader);
		Double sum = readSum("Please enter sum: ", reader);

		Double rateTo = rates.get(currencyTo);
		Double rateFrom = rates.get(currencyFrom);
		if (rateTo != null && rateFrom != null) {
			Double res = rateTo / rateFrom * sum;
			System.out.println(sum + " " + currencyFrom + " " + " = " + res + " " + currencyTo);
		} else {
			System.out.println("ERROR");
		}

	}

	private static String readCurrency(String message, BufferedReader reader) throws IOException {
		System.out.print(message);
		return reader.readLine().toUpperCase().trim();
	}

	private static Double readSum(String message, BufferedReader reader) throws IOException {
		System.out.print(message);
		return Double.valueOf(reader.readLine().trim());
	}

	private static void allCurrenciesPrint(HashMap<String, Double> rates) {

			System.out.println("Currencies available -\n");
			String keysString = String.join(" ", rates.keySet());
			System.out.println(keysString.replaceAll("(.{120})", "$1\n"));
			System.out.println();
	}
}
