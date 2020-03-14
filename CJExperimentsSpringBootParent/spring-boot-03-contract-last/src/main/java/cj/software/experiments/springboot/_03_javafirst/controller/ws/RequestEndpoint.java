package cj.software.experiments.springboot._03_javafirst.controller.ws;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import cj.software.experiments.springboot._03_javafirst.data.CalculationRequest;
import cj.software.experiments.springboot._03_javafirst.data.CalculationResponse;

@Endpoint
public class RequestEndpoint
		implements
		WSEndpoint
{
	private Logger logger = LogManager.getFormatterLogger();

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "calculateRequest")
	@ResponsePayload
	public CalculationResponse calculationRequest(@RequestPayload CalculationRequest pInput)
	{
		this.logger.info("key=%s, due=%s", pInput.getBusinessKey(), pInput.getDueTimestamp());
		Map<String, Double> lData = pInput.getData();
		Set<String> lKeySet = lData.keySet();
		SortedSet<String> lSorted = new TreeSet<>(lKeySet);
		for (String bKey : lSorted)
		{
			Double lValue = lData.get(bKey);
			this.logger.info("%s = %13.4f", bKey, lValue);
		}

		CalculationResponse lResult = new CalculationResponse();
		this.logger.info("return %s", lResult.getUuid());
		return lResult;
	}
}
