package cj.software.experiments.springboot._03_javafirst.data;

import java.time.LocalDateTime;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

public class CalculationRequestTest
{
	@Test
	public void dumpRequest() throws JAXBException
	{
		JAXBContext lCtx = JAXBContext.newInstance(CalculationRequest.class);
		CalculationRequest lInstance = this.buildInstance();
		Marshaller lMarshaller = lCtx.createMarshaller();
		lMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		lMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		lMarshaller.marshal(lInstance, System.out);
	}

	private CalculationRequest buildInstance()
	{
		CalculationRequest lResult = new CalculationRequest();
		lResult.setBusinessKey("47114712");
		lResult.setDueTimestamp(LocalDateTime.of(2020, 8, 31, 15, 0));
		lResult.addData("bliblablup", 123.45);
		lResult.addData("tralala", -3.456);
		return lResult;
	}
}
