package cj.software.experiments.springboot._03_javafirst.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.migesok.jaxb.adapter.javatime.LocalDateTimeXmlAdapter;

@XmlRootElement(name = "calculation-request-in")
@XmlType(name = "", propOrder =
{ "businessKey", "dueTimestamp", "data"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculationRequest
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "business-key", required = true)
	@NotNull
	@NotEmpty
	private String businessKey;

	@XmlElement(name = "due-timestamp", required = true)
	@NotNull
	@XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
	private LocalDateTime dueTimestamp;

	@XmlElementWrapper(name = "data")
	private Map<String, Double> data = new HashMap<>();

	public String getBusinessKey()
	{
		return this.businessKey;
	}

	public void setBusinessKey(String pBusinessKey)
	{
		this.businessKey = pBusinessKey;
	}

	public LocalDateTime getDueTimestamp()
	{
		return this.dueTimestamp;
	}

	public void setDueTimestamp(LocalDateTime pDueTimestamp)
	{
		this.dueTimestamp = pDueTimestamp;
	}

	public Map<String, Double> getData()
	{
		return this.data;
	}

	public Double addData(String pKey, Double pValue)
	{
		Double lResult = this.data.put(pKey, pValue);
		return lResult;
	}
}
