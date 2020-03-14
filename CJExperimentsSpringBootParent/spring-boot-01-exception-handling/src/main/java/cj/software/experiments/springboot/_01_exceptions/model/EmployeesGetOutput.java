package cj.software.experiments.springboot._01_exceptions.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "employees-get-output")
@XmlType(name = "", propOrder =
{ "employees"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesGetOutput
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElementWrapper(name = "employees", required = true)
	@NotNull
	@Valid
	private List<Employee> employees = new ArrayList<>();

	public List<Employee> getEmployees()
	{
		return this.employees;
	}

	public boolean addEmployees(Employee... pEmployees)
	{
		boolean lResult = this.employees.addAll(Arrays.asList(pEmployees));
		return lResult;
	}
}
