package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepo;

@Service

public class RegistrationService {

	@Autowired
	EmployeeRepo employeeRepo;

	public List<Employee> getAllEmployee() {

		return employeeRepo.findAll();

	}

	public Employee saveEmployee(Employee emp) {
		return employeeRepo.save(emp);

	}

	public Optional<Employee> getEmployee(Long id) {
		return employeeRepo.findById(id);
	}

	public Employee updateEmpDetail(Employee updateEmp, Long id) {
		return employeeRepo.findById(id).map(employee -> {
			employee.setFirstName(updateEmp.getFirstName());
			employee.setLastName(updateEmp.getLastName());
			employee.setPhoneNumber(updateEmp.getPhoneNumber());
			employee.setEmail(updateEmp.getEmail());
			employee.setAddress(updateEmp.getAddress());
			employee.setGender(updateEmp.getGender());
			employee.setPosition(updateEmp.getPosition());
			return employeeRepo.save(employee);
		}).orElseThrow(() -> new EmployeeNotFoundException("Employee with Id " + id + " does not exist!"));
	}

	public void deleteEmpRecordById(Long id) {

		if (employeeRepo.existsById(id)) {
			employeeRepo.deleteById(id);
		} else {
			throw new EmployeeNotFoundException("Employee with ID " + id + " does not exist!");
		}

	}

}
