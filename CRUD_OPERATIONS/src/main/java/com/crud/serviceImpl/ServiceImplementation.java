package com.crud.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crud.dao.CrudRepository;
import com.crud.dto.CrudDto;
import com.crud.model.Users;
import com.crud.service.CrudService;

@Service
public class ServiceImplementation implements CrudService {

	@Autowired
	private CrudRepository crudRepository;

	@Override
	public ResponseEntity<String> saveUser(CrudDto crudDto) {
		Users users = new Users();

		// Check UserId is Present
		Optional<Users> userId = crudRepository.findById(crudDto.getUserId());
		if (!userId.isPresent()) {
			users.setUserId(crudDto.getUserId());
		} else {
			return new ResponseEntity<>("User id Not Available...", HttpStatus.OK);
		}

		// Check Firstname and Lastname available
		Optional<Users> firstname = Optional.ofNullable(crudRepository.findByFirstName(crudDto.getFirstName()));
		Optional<Users> lastname = Optional.ofNullable(crudRepository.findByLastName(crudDto.getLastName()));
		if (!firstname.isPresent() && !lastname.isPresent()) {

			users.setFirstName(crudDto.getFirstName());
			users.setFirstName(crudDto.getFirstName());
		}
		// Check Username available
		Optional<Users> username = crudRepository.findByUserName(crudDto.getUserName());
		if (!username.isPresent()) {

			users.setUserName(crudDto.getUserName());
		} else {
			return new ResponseEntity<>("Username Not Available...", HttpStatus.OK);
		}

		// Check Email is Present
		Optional<Users> email = crudRepository.findByEmail(crudDto.getEmail());
		System.out.println(email);
		if (!email.isPresent()) {

			users.setEmail(crudDto.getEmail());
		} else {
			return new ResponseEntity<>("Email Already Used...", HttpStatus.OK);
		}

		// Check Contact Available
		Optional<Users> contact = crudRepository.findByContact(crudDto.getContact());
		if (!contact.isPresent()) {

			users.setLastName(crudDto.getLastName());
			users.setDob(crudDto.getDob());
			users.setContact(crudDto.getContact());
			users.setPassword(crudDto.getPassword());
			users.setCreatedOn(crudDto.getCreatedOn());
			users.setUpdatedOn(crudDto.getUpdatedOn());
			crudRepository.save(users);
			return new ResponseEntity<>("User Registered Successfully...", HttpStatus.OK);
		} else {

			return new ResponseEntity<>("Contact Already Registered...", HttpStatus.OK);
		}

	}

	// All Users Data Get
	@Override
	public List<Users> getData() {
		List<Users> list = crudRepository.findAll();
		return list;
	}

	// Data Get By Firstname
	@Override
	public Optional<Users> getByFirstName(String firstName) {

		return Optional.ofNullable(crudRepository.findByFirstName(firstName));
	}

	// Data Get By Lastname
	@Override
	public Optional<Users> getByLastName(String lastName) {

		return Optional.ofNullable(crudRepository.findByLastName(lastName));
	}

	// Data Get By User id
	@Override
	public Optional<Users> getByUserId(Long userId) {

		return crudRepository.findById(userId);
	}

	// Update By UserId
	@Override
	public ResponseEntity<String> updateUser(Long userId, CrudDto crudDto) {
		ResponseEntity<String> msg = new ResponseEntity<>("", HttpStatus.OK);
		Optional<Users> value = crudRepository.findById(userId);
		if (value.isPresent()) {
			Users users = crudRepository.getByUserId(userId);
			users.setFirstName(crudDto.getFirstName());
			users.setLastName(crudDto.getLastName());
			users.setDob(crudDto.getDob());
			users.setUpdatedOn(crudDto.getUpdatedOn());
			crudRepository.save(users);
			msg = new ResponseEntity<>("User Data Updated Successfully... ", HttpStatus.OK);
		} else {
			msg = new ResponseEntity<>("User Not Exist...", HttpStatus.OK);
		}
		return msg;
	}

	// Delete By User id
	@Override
	public ResponseEntity<String> deleteUser(Long userId) {
		crudRepository.deleteById(userId);
		return new ResponseEntity<>("User Data Deleted Successfully... ", HttpStatus.OK);
	}

}
