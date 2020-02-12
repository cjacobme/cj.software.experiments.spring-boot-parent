package cj.software.experiments.springboot._01_exceptions.datastore;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import cj.software.experiments.springboot._01_exceptions.model.Employee;

@Component
public class EmployeeStore
{
	private SortedMap<Long, Employee> entries = new TreeMap<>();

	@PostConstruct
	public void fillWithTestData()
	{
		this.addEmployee(new Employee(1l, "abc", "def", "abc.def@somewhere.earth"));
		this.addEmployee(new Employee(2l, "cba", "fed", "cba.fed@elsewhere.earth"));
	}

	public void addEmployee(Employee pEmployee)
	{
		if (pEmployee.getId() == null)
		{
			Long lId = this.entries.size() + 1l;
			pEmployee.setId(lId);
		}
		this.entries.put(pEmployee.getId(), pEmployee);
	}

	public SortedMap<Long, Employee> getEmployees()
	{
		return Collections.unmodifiableSortedMap(this.entries);
	}
}
