package cj.software.experiments.springboot._01_exceptions.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.jupiter.api.Test;

public class EmployeePostInputTest
{

	@Test
	public void dump() throws JAXBException
	{
		Employee lEmp = new Employee(123l, "abc", "def", "abc.def@asdf");
		EmployeePostInput lInput = new EmployeePostInput();
		lInput.setEmployee(lEmp);

		JAXBContext lCtx = JAXBContext.newInstance(EmployeePostInput.class);
		Marshaller lMarshaller = lCtx.createMarshaller();
		lMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		lMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		lMarshaller.marshal(lInput, System.out);
	}
}
