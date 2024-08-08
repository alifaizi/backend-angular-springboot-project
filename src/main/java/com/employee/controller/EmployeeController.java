package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.Employee;
import com.employee.service.RegistrationService;

@RestController
@RequestMapping("/employee")

public class EmployeeController {

	@Autowired

	RegistrationService registrationService;

	@GetMapping("/emp")
	public ResponseEntity<List<Employee>> getAllEmployees() {

		List<Employee> employees = registrationService.getAllEmployee();

		return ResponseEntity.ok(employees);
	}

	@PostMapping("/addEmp")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

		try {

			Employee saveEmployee = registrationService.saveEmployee(employee);

			return ResponseEntity.status(HttpStatus.CREATED).body(saveEmployee);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/emp/{id}")

	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

		Optional<Employee> optionalEmployee = registrationService.getEmployee(id);

		return optionalEmployee.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@PutMapping("/emp/updateEmp/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employeeDetail,
			@PathVariable("id") Long id) {
		try {
			Employee updatedEmployee = registrationService.updateEmpDetail(employeeDetail, id);
			return ResponseEntity.ok(updatedEmployee);
		} catch (EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/emp/deletEmp/{id}")
	public ResponseEntity<Employee> deleteEmpById(@PathVariable Long id) {
		try {
			registrationService.deleteEmpRecordById(id);
			return ResponseEntity.noContent().build();
		} catch (EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
