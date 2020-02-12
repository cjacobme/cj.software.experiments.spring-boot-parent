package cj.software.experiments.springboot._01_exceptions.controller;

import java.util.Map.Entry;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cj.software.experiments.springboot._01_exceptions.datastore.EmployeeStore;
import cj.software.experiments.springboot._01_exceptions.model.Employee;
import cj.software.experiments.springboot._01_exceptions.model.EmployeesGetOutput;

@RestController
public class EmployeeController
{
	@Autowired
	private EmployeeStore store;

	@GetMapping(path = "employees", produces = MediaType.APPLICATION_XML_VALUE)
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
}
