package cj.software.experiments.springboot.soap.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import cj.software.experiments.springboot.soap.repository.CountryRepository;
import soap.experiments.software.cj.countries.Country;
import soap.experiments.software.cj.countries.GetCountryRequest;
import soap.experiments.software.cj.countries.GetCountryResponse;

@Endpoint
public class CountryEndpoint
{
	private static final String NAMESPACE_URI = "http://cj.software.experiments.soap/countries";

	@Autowired
	private CountryRepository countryRepository;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest pRequest)
	{
		String lSearchedName = pRequest.getName();
		Country lFound = this.countryRepository.findCountry(lSearchedName);
		GetCountryResponse lResult = new GetCountryResponse();
		lResult.setCountry(lFound);
		return lResult;
	}
}
