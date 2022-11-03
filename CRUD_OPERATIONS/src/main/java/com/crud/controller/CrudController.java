package com.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.dto.CrudDto;
import com.crud.model.Users;
import com.crud.service.CrudService;

@RestController
public class CrudController {

	@Autowired
	private CrudService crudService;

	// Insert User Data
	@PostMapping("/saveuser")
	public ResponseEntity<String> save(@RequestBody CrudDto crudDto) {

		return crudService.saveUser(crudDto);  

	}

	// Get all User Data
	@GetMapping("/users")
	public List<Users> allUsers() {

		return crudService.getData();
	}

	// Get user Data By using Firstname
	@GetMapping("/getbyfname/{firstName}")
	public Optional<Users> fname(@PathVariable("firstName") String firstName) {

		return crudService.getByFirstName(firstName);
	}

	// Get user Data By using Lastname
	@GetMapping("/getbylname/{lastName}")
	public Optional<Users> lname(@PathVariable("lastName") String lastName) {

		return crudService.getByLastName(lastName);
	}

	// User User Data using UserId
	@GetMapping("/getbyid/{userId}")
	public Optional<Users> idData(@PathVariable("userId") Long userId) {

		return crudService.getByUserId(userId);
	}

	// Update user details
	@PutMapping("/update/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable("userId") Long userId, @RequestBody CrudDto crudDto) {
		return crudService.updateUser(userId, crudDto);

	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
		return crudService.deleteUser(userId);
	}

}
