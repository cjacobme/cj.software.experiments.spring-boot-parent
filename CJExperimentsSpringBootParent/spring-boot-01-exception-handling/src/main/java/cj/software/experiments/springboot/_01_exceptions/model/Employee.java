package cj.software.experiments.springboot._01_exceptions.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "employee", propOrder =
{ "id", "firstName", "lastName", "email"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlAttribute(name = "id")
	private Long id;

	@NotNull
	@NotEmpty
	@XmlElement(name = "first-name")
	private String firstName;

	@NotNull
	@NotEmpty
	@XmlElement(name = "last-name")
	private String lastName;

	@NotNull
	@Email
	@XmlElement(name = "email")
	private String email;

	public Employee()
	{
	}

	public Employee(Long pId, String pFirstName, String pLastName, String pEmail)
	{
		this.id = pId;
		this.firstName = pFirstName;
		this.lastName = pLastName;
		this.email = pEmail;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long pId)
	{
		this.id = pId;
	}

	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName(String pFirstName)
	{
		this.firstName = pFirstName;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public void setLastName(String pLastName)
	{
		this.lastName = pLastName;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String pEmail)
	{
		this.email = pEmail;
	}
}
