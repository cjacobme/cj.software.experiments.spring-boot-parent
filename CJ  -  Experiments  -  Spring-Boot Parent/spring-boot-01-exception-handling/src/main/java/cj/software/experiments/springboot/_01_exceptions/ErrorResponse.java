package cj.software.experiments.springboot._01_exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "error-resonse")
@XmlType(name = "", propOrder =
{ "message", "details"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorResponse
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "message")
	private String message;

	@XmlElementWrapper(name = "details")
	@XmlElement(name = "entry")
	private List<String> details = new ArrayList<>();

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String pMessage)
	{
		this.message = pMessage;
	}

	public List<String> getDetails()
	{
		return this.details;
	}

	public boolean addDetail(String pDetail)
	{
		boolean lResult = this.details.add(pDetail);
		return lResult;
	}
}
