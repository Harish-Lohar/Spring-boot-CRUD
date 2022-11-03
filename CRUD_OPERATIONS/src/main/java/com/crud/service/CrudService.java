package com.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.crud.dto.CrudDto;
import com.crud.model.Users;

public interface CrudService {

	ResponseEntity<String> saveUser(CrudDto crudDto);

	List<Users> getData();

	Optional<Users> getByFirstName(String firstName);

	Optional<Users> getByLastName(String lastName);

	Optional<Users> getByUserId(Long userId);

	ResponseEntity<String> updateUser(Long userId, CrudDto crudDto);

	ResponseEntity<String> deleteUser(Long userId);

	

}
