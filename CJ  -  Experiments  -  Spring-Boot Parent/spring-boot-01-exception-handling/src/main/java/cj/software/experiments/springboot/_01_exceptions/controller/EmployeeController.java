package cj.software.experiments.springboot._01_exceptions.controller;

import java.util.Map.Entry;
import java.util.SortedMap;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cj.software.experiments.springboot._01_exceptions.datastore.EmployeeStore;
import cj.software.experiments.springboot._01_exceptions.model.Employee;
import cj.software.experiments.springboot._01_exceptions.model.EmployeePostInput;
import cj.software.experiments.springboot._01_exceptions.model.EmployeesDetailGetOutput;
import cj.software.experiments.springboot._01_exceptions.model.EmployeesGetOutput;

@RestController
public class EmployeeController
{
	@Autowired
	private EmployeeStore store;

	@GetMapping(value = "employees", produces = MediaType.APPLICATION_XML_VALUE)
	public EmployeesGetOutput listEmployees()
	{
		EmployeesGetOutput lResult = new EmployeesGetOutput();
		SortedMap<Long, Employee> lEmployees = this.store.getEmployees();
		for (Entry<Long, Employee> bEmp : lEmployees.entrySet())
		{
			Employee lEntry = bEmp.getValue();
			lResult.addEmployees(lEntry);
		}
		return lResult;
	}

	@GetMapping(value = "employees/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public @Valid EmployeesDetailGetOutput getDetails(@PathVariable(name = "id") Long pId)
	{
		Employee lFound = this.store.getEmployee(pId);
		if (lFound == null)
		{
			throw new NotFoundException(String.format("Employee with id %d not found", pId));
		}
		EmployeesDetailGetOutput lResult = new EmployeesDetailGetOutput();
		lResult.setEmployee(lFound);
		return lResult;
	}

	@PostMapping(value = "employees", consumes = MediaType.APPLICATION_XML_VALUE)
	public Long saveEmployee(@NotNull @Valid @RequestBody EmployeePostInput pInput)
	{
		long lResult = this.store.addEmployee(pInput.getEmployee());
		return lResult;
	}
}
