package cj.software.experiments.springboot._01_exceptions.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "employees-detail-get-output")
@XmlType(name = "", propOrder =
{ "employee"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesDetailGetOutput
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "employee", required = true)
	@NotNull
	@Valid
	private Employee employee;

	public Employee getEmployee()
	{
		return this.employee;
	}

	public void setEmployee(Employee pEmployee)
	{
		this.employee = pEmployee;
	}
}
