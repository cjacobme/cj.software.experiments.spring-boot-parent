package cj.software.experiments.springboot._03_javafirst.data;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "calculation-request-out")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder =
{ "id"
})
public class CalculationResponse
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "id", required = true)
	@NotNull
	private UUID uuid;

	public UUID getUuid()
	{
		return this.uuid;
	}

	public CalculationResponse()
	{
		this.uuid = UUID.randomUUID();
	}
}
