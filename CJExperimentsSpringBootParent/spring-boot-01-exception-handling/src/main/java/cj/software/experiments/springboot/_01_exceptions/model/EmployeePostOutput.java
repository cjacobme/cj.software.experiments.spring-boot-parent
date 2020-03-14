package cj.software.experiments.springboot._01_exceptions.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "employees-post-output")
@XmlType(name = "", propOrder =
{ "id"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeePostOutput
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "id", required = true)
	private long id;

	public long getId()
	{
		return this.id;
	}

	public void setId(long pId)
	{
		this.id = pId;
	}

}
