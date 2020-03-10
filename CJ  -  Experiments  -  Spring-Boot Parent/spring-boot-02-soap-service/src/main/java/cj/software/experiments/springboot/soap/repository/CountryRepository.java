package cj.software.experiments.springboot.soap.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import soap.experiments.software.cj.countries.Country;
import soap.experiments.software.cj.countries.Currency;

@Component
public class CountryRepository
{
	private static final Map<String, Country> entries = new HashMap<>();

	@PostConstruct
	public void initData()
	{
		this.putCountry(this.createCountry("Spain", "Madrid", Currency.EUR, 46704314));
		this.putCountry(this.createCountry("Poland", "Warsaw", Currency.PLN, 38186860));
		this.putCountry(this.createCountry("UK", "London", Currency.GBP, 63705000));
	}

	private Country createCountry(
			String pName,
			String pCapital,
			Currency pCurrency,
			int pPopulation)
	{
		Country lResult = new Country();
		lResult.setName(pName);
		lResult.setCapital(pCapital);
		lResult.setCurrency(pCurrency);
		lResult.setPopulation(pPopulation);
		return lResult;
	}

	private void putCountry(Country pCountry)
	{
		entries.put(pCountry.getName(), pCountry);
	}

	public Country findCountry(String pName)
	{
		Country lResult = entries.get(pName);
		return lResult;
	}
}
